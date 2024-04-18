package edu.yuriiknowsjava.xpinjection.conferences.controllers;

import java.math.BigInteger;

import edu.yuriiknowsjava.xpinjection.conferences.dto.ConferenceDto;
import edu.yuriiknowsjava.xpinjection.conferences.dto.CreationConferenceDto;
import edu.yuriiknowsjava.xpinjection.conferences.services.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/conferences", produces = MediaType.APPLICATION_JSON_VALUE)
class ConferencesController {
    private final ConferenceService conferenceService;

    @Autowired
    public ConferencesController(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @GetMapping
    public Page<ConferenceDto> getConferences(Pageable pageable) {
        return conferenceService.getConferences(pageable);
    }

    @PostMapping
    public ConferenceDto createConference(@RequestBody CreationConferenceDto creationConferenceDto) {
        return conferenceService.createConference(creationConferenceDto);
    }

    @PutMapping("/{id}")
    public ConferenceDto updateConference(@PathVariable BigInteger id, @RequestBody ConferenceDto conferenceDto) {
        conferenceDto = conferenceDto.withId(id);
        return conferenceService.updateConference(conferenceDto);
    }
}
