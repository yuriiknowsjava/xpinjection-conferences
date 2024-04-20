package edu.yuriiknowsjava.xpinjection.conferences.controllers;

import edu.yuriiknowsjava.xpinjection.conferences.dto.ThemeDto;
import edu.yuriiknowsjava.xpinjection.conferences.services.ThemeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Validated
@RestController
@RequestMapping(path = "/themes", produces = MediaType.APPLICATION_JSON_VALUE)
class ThemesController {
    private final ThemeService themeService;

    @GetMapping
    ResponseEntity<?> getThemes(Pageable pageable) {
        return ResponseEntity.ok(themeService.getThemes(pageable));
    }
}
