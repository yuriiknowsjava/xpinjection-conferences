package edu.yuriiknowsjava.xpinjection.conferences.dto;

import java.time.ZonedDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreationConferenceDto(
        @NotBlank(message = "Conference name cannot be blank") String name,
        @NotNull ZonedDateTime startDate,
        @NotNull ZonedDateTime endDate,
        String description,
        @NotNull(message = CONFERENCE_SHOULD_HAVE_AT_LEAST_ONE_THEME)
        @NotEmpty(message = CONFERENCE_SHOULD_HAVE_AT_LEAST_ONE_THEME) List<ThemeDto> themes
) {
    public CreationConferenceDto {
        name = name == null ? null : name.strip();
        description = description == null ? "" : description.strip();
        themes = themes == null ? List.of() : themes;
    }

    private static final String CONFERENCE_SHOULD_HAVE_AT_LEAST_ONE_THEME = "Conference should have at least one theme";
}
