package edu.yuriiknowsjava.xpinjection.conferences.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
class TalksController {

    @GetMapping("/conferences/{id}/talks")
    public ResponseEntity<?> getConferenceTalks(@PathVariable int id) {
        return null;
    }

    @PostMapping("/conference/{id}/talk")
    public ResponseEntity<?> addTalkToConference(@PathVariable int id) {
        return null;
    }
}
