package edu.yuriiknowsjava.xpinjection.conferences.dto;

import java.time.ZonedDateTime;

public record CreationConferenceDto(
        String name,
        ZonedDateTime startDate,
        ZonedDateTime endDate,
        String description
) {
    public CreationConferenceDto {
        name = name == null ? null : name.strip();
        description = description == null ? "" : description.strip();
    }
}
