package edu.yuriiknowsjava.xpinjection.conferences.controllers;

import edu.yuriiknowsjava.xpinjection.conferences.services.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThemesController {
    private final ThemeService themeService;

    @Autowired
    public ThemesController(ThemeService themeService) {
        this.themeService = themeService;
    }

    @GetMapping(path = "/themes", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getThemes(Pageable pageable) {
        return themeService.getThemes(pageable);
    }
}
