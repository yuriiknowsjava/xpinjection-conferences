package edu.yuriiknowsjava.xpinjection.conferences.dto;

import static edu.yuriiknowsjava.xpinjection.conferences.dto.ConferenceBeanValidationMessages.CONFERENCE_END_DATE_CANNOT_BE_NULL;
import static edu.yuriiknowsjava.xpinjection.conferences.dto.ConferenceBeanValidationMessages.CONFERENCE_NAME_CANNOT_BE_BLANK;
import static edu.yuriiknowsjava.xpinjection.conferences.dto.ConferenceBeanValidationMessages.CONFERENCE_SHOULD_HAVE_AT_LEAST_ONE_THEME;
import static edu.yuriiknowsjava.xpinjection.conferences.dto.ConferenceBeanValidationMessages.CONFERENCE_START_DATE_CANNOT_BE_NULL;

import java.time.ZonedDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ConferenceCreationDto(
        @NotBlank(message = CONFERENCE_NAME_CANNOT_BE_BLANK) String name,
        @NotNull(message = CONFERENCE_START_DATE_CANNOT_BE_NULL) ZonedDateTime startDate,
        @NotNull(message = CONFERENCE_END_DATE_CANNOT_BE_NULL) ZonedDateTime endDate,
        String description,
        @NotNull(message = CONFERENCE_SHOULD_HAVE_AT_LEAST_ONE_THEME)
        @NotEmpty(message = CONFERENCE_SHOULD_HAVE_AT_LEAST_ONE_THEME) List<ThemeAdditionDto> themes
) {
    public ConferenceCreationDto {
        name = name == null ? null : name.strip();
        description = description == null ? "" : description.strip();
        themes = themes == null ? List.of() : themes;
    }
}
