package edu.yuriiknowsjava.xpinjection.conferences.services;

import edu.yuriiknowsjava.xpinjection.conferences.dto.ConferenceDto;
import edu.yuriiknowsjava.xpinjection.conferences.dto.CreationConferenceDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ConferenceService {

    Page<ConferenceDto> getConferences(Pageable pageable);

    ConferenceDto createConference(CreationConferenceDto creationConferenceDto);

    ConferenceDto updateConference(ConferenceDto creationConferenceDto);
}
