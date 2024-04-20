package edu.yuriiknowsjava.xpinjection.conferences.services;

import java.util.Optional;

import edu.yuriiknowsjava.xpinjection.conferences.dto.TalkCreationDto;
import edu.yuriiknowsjava.xpinjection.conferences.dto.TalkDto;
import edu.yuriiknowsjava.xpinjection.conferences.entities.Conference;
import edu.yuriiknowsjava.xpinjection.conferences.entities.Talk;
import edu.yuriiknowsjava.xpinjection.conferences.exceptions.EntityAlreadyExistsException;
import edu.yuriiknowsjava.xpinjection.conferences.exceptions.EntityDoesNotExist;
import edu.yuriiknowsjava.xpinjection.conferences.mappers.TalkMapper;
import edu.yuriiknowsjava.xpinjection.conferences.mappers.TalkTypeMapper;
import edu.yuriiknowsjava.xpinjection.conferences.repositories.ConferenceRepository;
import edu.yuriiknowsjava.xpinjection.conferences.repositories.TalkRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class TalkServiceImpl implements TalkService {
    private final TalkRepository talkRepository;
    private final ConferenceRepository conferenceRepository;

    @Override
    public Page<TalkDto> getTalks(Long conferenceId, Pageable pageable) {
        Page<Talk> talks = talkRepository.findByConferenceId(conferenceId, pageable);
        return talks.map(TalkMapper::toDto);
    }

    @Transactional
    @Override
    public TalkDto createTalk(Long conferenceId, TalkCreationDto talkCreationDto) {
        Optional<Conference> conferenceOptional = conferenceRepository.findById(conferenceId);
        if (conferenceOptional.isEmpty()) {
            throw new EntityDoesNotExist("Conference not found by id: " + conferenceId);
        }
        Conference conference = conferenceOptional.get();
        if (talkRepository.existsByConferenceIdAndTitle(conferenceId, talkCreationDto.title())) {
            var msg = "There is a talk with the same title in the scope of " + conference.getName() + " conference";
            throw new EntityAlreadyExistsException(msg);
        }
        var talk = new Talk();
        talk.setTitle(talkCreationDto.title());
        talk.setDescription(talkCreationDto.description());
        talk.setTalkType(TalkTypeMapper.fromDto(talkCreationDto.talkType()));
        talk.setConference(conference);
        Talk result = talkRepository.save(talk);
        return TalkMapper.toDto(result);
    }
}
