package edu.yuriiknowsjava.xpinjection.conferences.controllers;

import edu.yuriiknowsjava.xpinjection.conferences.dto.ConferenceCreationDto;
import edu.yuriiknowsjava.xpinjection.conferences.dto.ConferenceReplacementDto;
import edu.yuriiknowsjava.xpinjection.conferences.services.ConferenceService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Validated
@RestController
@RequestMapping(path = "/conferences", produces = MediaType.APPLICATION_JSON_VALUE)
class ConferencesController {
    private final ConferenceService conferenceService;

    @GetMapping
    public ResponseEntity<?> getConferences(Pageable pageable) {
        return ResponseEntity.ok(conferenceService.getConferences(pageable));
    }

    @PostMapping
    public ResponseEntity<?> createConference(@RequestBody @Valid ConferenceCreationDto conferenceCreationDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(conferenceService.createConference(conferenceCreationDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateConference(@PathVariable Long id, @RequestBody @Valid ConferenceReplacementDto conferenceReplacementDto) {
        return ResponseEntity.ok(conferenceService.updateConference(id, conferenceReplacementDto));
    }
}
