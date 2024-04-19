package edu.yuriiknowsjava.xpinjection.conferences.services;

import edu.yuriiknowsjava.xpinjection.conferences.dto.ConferenceDto;
import edu.yuriiknowsjava.xpinjection.conferences.dto.ConferenceCreationDto;
import edu.yuriiknowsjava.xpinjection.conferences.dto.ConferenceReplacementDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ConferenceService {

    Page<ConferenceDto> getConferences(Pageable pageable);

    ConferenceDto createConference(ConferenceCreationDto conferenceCreationDto);

    ConferenceDto updateConference(Long id, ConferenceReplacementDto creationConferenceDto);
}
