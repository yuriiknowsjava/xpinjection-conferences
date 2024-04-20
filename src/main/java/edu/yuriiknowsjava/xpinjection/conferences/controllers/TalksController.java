package edu.yuriiknowsjava.xpinjection.conferences.controllers;

import edu.yuriiknowsjava.xpinjection.conferences.dto.TalkCreationDto;
import edu.yuriiknowsjava.xpinjection.conferences.services.TalkService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Validated
@RestController
class TalksController {
    private final TalkService talkService;

    @GetMapping("/conferences/{id}/talks")
    ResponseEntity<?> getConferenceTalks(@PathVariable Long id, Pageable pageable) {
        return ResponseEntity.ok(talkService.getTalks(id, pageable));
    }

    @PostMapping("/conferences/{conferenceId}/talks")
    ResponseEntity<?> addTalkToConference(@PathVariable Long conferenceId, @RequestBody @Valid TalkCreationDto talkCreationDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(talkService.createTalk(conferenceId, talkCreationDto));
    }
}
