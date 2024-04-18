package edu.yuriiknowsjava.xpinjection.conferences.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class TalksController {

    @GetMapping("/conferences/{id}/talks")
    public Object getConferenceTalks(@PathVariable int id) {
        return null;
    }

    @PostMapping("/conference/{id}/talk")
    public Object addTalkToConference(@PathVariable int id) {
        return null;
    }
}
