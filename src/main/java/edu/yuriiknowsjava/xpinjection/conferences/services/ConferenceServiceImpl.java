package edu.yuriiknowsjava.xpinjection.conferences.services;

import java.util.Optional;

import edu.yuriiknowsjava.xpinjection.conferences.dto.ConferenceDto;
import edu.yuriiknowsjava.xpinjection.conferences.dto.CreationConferenceDto;
import edu.yuriiknowsjava.xpinjection.conferences.entities.Conference;
import edu.yuriiknowsjava.xpinjection.conferences.exceptions.EntityAlreadyExistsException;
import edu.yuriiknowsjava.xpinjection.conferences.mappers.ConferenceMapper;
import edu.yuriiknowsjava.xpinjection.conferences.repositories.ConferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConferenceServiceImpl implements ConferenceService {
    private final ConferenceRepository conferenceRepository;

    @Autowired
    public ConferenceServiceImpl(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }

    @Transactional
    @Override
    public Page<ConferenceDto> getConferences(Pageable pageable) {
        Page<Conference> conferences = conferenceRepository.findAll(pageable);
        return conferences.map(ConferenceMapper::toDto);
    }

    @Transactional
    @Override
    public ConferenceDto createConference(CreationConferenceDto creationConferenceDto) {
        if (conferenceRepository.existsByName(creationConferenceDto.name())) {
            throw new EntityAlreadyExistsException("conference with name <" + creationConferenceDto.name() + "> already exists");
        }
        var conferenceToCreate = new Conference();
        conferenceToCreate.setName(creationConferenceDto.name());
        conferenceToCreate.setStartDate(creationConferenceDto.startDate());
        conferenceToCreate.setEndDate(creationConferenceDto.endDate());
        conferenceToCreate.setDescription(creationConferenceDto.description());
        Conference conference = conferenceRepository.save(conferenceToCreate);
        return ConferenceMapper.toDto(conference);
    }

    @Transactional
    @Override
    public ConferenceDto updateConference(ConferenceDto conferenceDtoToUpdate) {
        Optional<Conference> conferenceOptional = conferenceRepository.findById(conferenceDtoToUpdate.id());
        Conference conference = conferenceOptional.map(conf -> {
                    conf.setName(conferenceDtoToUpdate.name());
                    conf.setStartDate(conferenceDtoToUpdate.startDate());
                    conf.setEndDate(conferenceDtoToUpdate.endDate());
                    conf.setDescription(conferenceDtoToUpdate.description());
                    return conf;
                })
                .map(conferenceRepository::save)
                .orElseThrow(() -> new EntityAlreadyExistsException(String.format("Conference not found by ID %d", conferenceDtoToUpdate.id())));
        return ConferenceMapper.toDto(conference);
    }
}
