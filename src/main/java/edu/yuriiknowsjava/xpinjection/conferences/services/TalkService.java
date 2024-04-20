package edu.yuriiknowsjava.xpinjection.conferences.services;

import edu.yuriiknowsjava.xpinjection.conferences.dto.TalkCreationDto;
import edu.yuriiknowsjava.xpinjection.conferences.dto.TalkDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface TalkService {

    Page<TalkDto> getTalks(Long conferenceId, Pageable pageable);

    TalkDto createTalk(Long conferenceId, TalkCreationDto talkCreationDto);
}
