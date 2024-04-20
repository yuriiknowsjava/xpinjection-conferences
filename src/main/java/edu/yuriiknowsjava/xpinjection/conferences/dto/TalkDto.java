package edu.yuriiknowsjava.xpinjection.conferences.dto;

import edu.yuriiknowsjava.xpinjection.conferences.entities.TalkType;

public record TalkDto(
        Long id,
        String title,
        String description,
        TalkType talkType
) {
    public TalkDto {
        title = title == null ? null : title.strip();
        description = description == null ? "" : description.strip();
    }
}
