package edu.yuriiknowsjava.xpinjection.conferences.services;

import java.math.BigInteger;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import edu.yuriiknowsjava.xpinjection.conferences.configurations.ConferencesConfiguration;
import edu.yuriiknowsjava.xpinjection.conferences.dto.ConferenceDto;
import edu.yuriiknowsjava.xpinjection.conferences.dto.ConferenceCreationDto;
import edu.yuriiknowsjava.xpinjection.conferences.dto.ConferenceReplacementDto;
import edu.yuriiknowsjava.xpinjection.conferences.dto.ThemeAdditionDto;
import edu.yuriiknowsjava.xpinjection.conferences.entities.Conference;
import edu.yuriiknowsjava.xpinjection.conferences.entities.Theme;
import edu.yuriiknowsjava.xpinjection.conferences.exceptions.BusinessValidationException;
import edu.yuriiknowsjava.xpinjection.conferences.exceptions.EntityAlreadyExistsException;
import edu.yuriiknowsjava.xpinjection.conferences.exceptions.EntityDoesNotExist;
import edu.yuriiknowsjava.xpinjection.conferences.mappers.ConferenceMapper;
import edu.yuriiknowsjava.xpinjection.conferences.repositories.ConferenceRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ConferenceServiceImpl implements ConferenceService {
    private final ConferenceRepository conferenceRepository;
    private final ThemeService themeService;
    private final ConferencesConfiguration conferencesConfiguration;

    @Transactional
    @Override
    public Page<ConferenceDto> getConferences(Pageable pageable) {
        // Use FetchMode.SUBSELECT to avoid the N+1 problem and the in memory pagination issue
        Page<Conference> conferences = conferenceRepository.findAll(pageable);
        return conferences.map(ConferenceMapper::toDto);
    }

    @Transactional
    @Override
    public ConferenceDto createConference(ConferenceCreationDto conferenceCreationDto) {
        validateThemes(conferenceCreationDto.themes());
        validateConferenceDates(conferenceCreationDto.startDate(), conferenceCreationDto.endDate());
        if (conferenceRepository.existsByName(conferenceCreationDto.name())) {
            throw new EntityAlreadyExistsException("Conference with name <" + conferenceCreationDto.name() + "> already exists");
        }
        var conferenceToCreate = new Conference();
        conferenceToCreate.setName(conferenceCreationDto.name());
        conferenceToCreate.setStartDate(conferenceCreationDto.startDate());
        conferenceToCreate.setEndDate(conferenceCreationDto.endDate());
        conferenceToCreate.setDescription(conferenceCreationDto.description());
        Conference conference = conferenceRepository.save(conferenceToCreate);
        Set<Theme> themes = themeService.getThemes(conferenceCreationDto.themes());

        // FIXME: fix N+1 issue
        themes.forEach(conferenceToCreate::addTheme);
        return ConferenceMapper.toDto(conference);
    }

    private void validateConferenceDates(ZonedDateTime startDate, ZonedDateTime endDate) {
        if (startDate == null || endDate == null) {
            throw new BusinessValidationException("Start date and end date cannot be null");
        }
        if (startDate.isAfter(ZonedDateTime.now())) {
            throw new BusinessValidationException("Start date cannot be in the past");
        }
        if (startDate.isAfter(endDate)) {
            throw new BusinessValidationException("start date cannot be after end date");
        }
    }

    private void validateThemes(List<ThemeAdditionDto> themeAdditions) {
        if (themeAdditions.isEmpty()) {
            throw new BusinessValidationException("Conference should have at least one theme");
        }
        int maxThemeConfiguration = conferencesConfiguration.getMaxConferenceThemes();
        if (themeAdditions.size() > maxThemeConfiguration) {
            throw new BusinessValidationException(String.format("Conference should have at most %s themes", maxThemeConfiguration));
        }
        for (int i = 0; i < themeAdditions.size(); i++) {
            var themeAddition = themeAdditions.get(i);
            if (themeAddition.id() == null && !StringUtils.isBlank(themeAddition.tag())) {
                throw new BusinessValidationException(String.format(
                        "Conference theme with array index %s should have either \"id\" or \"tag\" specified "
                                + "when adding a theme to the conference", i
                ));
            }
        }
    }

    @Transactional
    @Override
    public ConferenceDto updateConference(BigInteger id, ConferenceReplacementDto conferenceDtoToReplace) {
        Optional<Conference> conferenceOptional = conferenceRepository.findById(id);
        if (conferenceOptional.isEmpty()) {
            throw new EntityDoesNotExist(String.format("Conference not found by ID %d", id));
        }
        if (conferenceRepository.existsByName(conferenceDtoToReplace.name())) {
            throw new EntityAlreadyExistsException("Conference with name <" + conferenceDtoToReplace.name() + "> already exists");
        }
        Conference conferenceToUpdate = conferenceOptional.get();
        conferenceToUpdate.setName(conferenceDtoToReplace.name());
        conferenceToUpdate.setStartDate(conferenceDtoToReplace.startDate());
        conferenceToUpdate.setEndDate(conferenceDtoToReplace.endDate());
        conferenceToUpdate.setDescription(conferenceDtoToReplace.description());
        Conference result = conferenceRepository.save(conferenceToUpdate);
        return ConferenceMapper.toDto(result);
    }
}
