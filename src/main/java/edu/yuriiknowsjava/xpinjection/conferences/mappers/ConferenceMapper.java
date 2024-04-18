package edu.yuriiknowsjava.xpinjection.conferences.mappers;

import java.util.stream.Collectors;

import edu.yuriiknowsjava.xpinjection.conferences.dto.ConferenceDto;
import edu.yuriiknowsjava.xpinjection.conferences.entities.Conference;

public class ConferenceMapper {
    public static ConferenceDto toDto(Conference conference) {
        return new ConferenceDto(
                conference.getId(),
                conference.getName(),
                conference.getStartDate(),
                conference.getEndDate(),
                conference.getDescription(),
                conference.getThemes().stream().map(ThemeMapper::toDto).collect(Collectors.toSet()),
                0 // FIXME count
        );
    }
}
