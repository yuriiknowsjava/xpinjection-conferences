package edu.yuriiknowsjava.xpinjection.conferences.dto;

import java.math.BigInteger;
import java.time.ZonedDateTime;
import java.util.Set;

public record ConferenceDto(
        BigInteger id,
        String name,
        ZonedDateTime startDate,
        ZonedDateTime endDate,
        String description,
        Set<ThemeDto> themes,
        int speakersCount
) {
    public ConferenceDto {
        name = name == null ? null : name.strip();
        description = description == null ? "" : description.strip();
        themes = themes == null ? Set.of() : themes;
    }
}
