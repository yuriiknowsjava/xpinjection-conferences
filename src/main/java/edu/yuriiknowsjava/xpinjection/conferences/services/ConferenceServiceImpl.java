package edu.yuriiknowsjava.xpinjection.conferences.services;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import edu.yuriiknowsjava.xpinjection.conferences.configurations.ConferencesConfiguration;
import edu.yuriiknowsjava.xpinjection.conferences.dto.ConferenceDto;
import edu.yuriiknowsjava.xpinjection.conferences.dto.CreationConferenceDto;
import edu.yuriiknowsjava.xpinjection.conferences.dto.ThemeDto;
import edu.yuriiknowsjava.xpinjection.conferences.entities.Conference;
import edu.yuriiknowsjava.xpinjection.conferences.exceptions.BusinessValidationException;
import edu.yuriiknowsjava.xpinjection.conferences.exceptions.EntityAlreadyExistsException;
import edu.yuriiknowsjava.xpinjection.conferences.exceptions.EntityDoesNotExist;
import edu.yuriiknowsjava.xpinjection.conferences.mappers.ConferenceMapper;
import edu.yuriiknowsjava.xpinjection.conferences.repositories.ConferenceRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ConferenceServiceImpl implements ConferenceService {
    private final ConferenceRepository conferenceRepository;
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
    public ConferenceDto createConference(CreationConferenceDto creationConferenceDto) {
        validateThemes(creationConferenceDto.themes());
        validateConferenceDates(creationConferenceDto.startDate(), creationConferenceDto.endDate());
        if (conferenceRepository.existsByName(creationConferenceDto.name())) {
            throw new EntityAlreadyExistsException("Conference with name <" + creationConferenceDto.name() + "> already exists");
        }
        var conferenceToCreate = new Conference();
        conferenceToCreate.setName(creationConferenceDto.name());
        conferenceToCreate.setStartDate(creationConferenceDto.startDate());
        conferenceToCreate.setEndDate(creationConferenceDto.endDate());
        conferenceToCreate.setDescription(creationConferenceDto.description());
        Conference conference = conferenceRepository.save(conferenceToCreate);
        return ConferenceMapper.toDto(conference);
    }

    private void validateConferenceDates(ZonedDateTime startDate, ZonedDateTime endDate) {
        if (startDate.isAfter(endDate)) {
            throw new BusinessValidationException("start date cannot be after end date");
        }
    }

    private void validateThemes(List<ThemeDto> themeDto) {
        if (themeDto.isEmpty()) {
            throw new BusinessValidationException("Conference should have at least one theme");
        }
        int maxThemeConfiguration = conferencesConfiguration.getMaxConferenceThemes();
        if (themeDto.size() > maxThemeConfiguration) {
            throw new BusinessValidationException(String.format("Conference should have at most %s themes", maxThemeConfiguration));
        }
    }

    @Transactional
    @Override
    public ConferenceDto updateConference(ConferenceDto conferenceDtoToUpdate) {
        Optional<Conference> conferenceOptional = conferenceRepository.findById(conferenceDtoToUpdate.id());
        if (conferenceOptional.isEmpty()) {
            throw new EntityDoesNotExist(String.format("Conference not found by ID %d", conferenceDtoToUpdate.id()));
        }
        if (conferenceRepository.existsByName(conferenceDtoToUpdate.name())) {
            throw new EntityAlreadyExistsException("Conference with name <" + conferenceDtoToUpdate.name() + "> already exists");
        }
        Conference conferenceToUpdate = conferenceOptional.get();
        conferenceToUpdate.setName(conferenceDtoToUpdate.name());
        conferenceToUpdate.setStartDate(conferenceDtoToUpdate.startDate());
        conferenceToUpdate.setEndDate(conferenceDtoToUpdate.endDate());
        conferenceToUpdate.setDescription(conferenceDtoToUpdate.description());
        Conference result = conferenceRepository.save(conferenceToUpdate);
        return ConferenceMapper.toDto(result);
    }
}
