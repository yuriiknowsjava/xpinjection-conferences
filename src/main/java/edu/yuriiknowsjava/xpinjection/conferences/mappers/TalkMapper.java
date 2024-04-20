package edu.yuriiknowsjava.xpinjection.conferences.mappers;

import edu.yuriiknowsjava.xpinjection.conferences.dto.TalkDto;
import edu.yuriiknowsjava.xpinjection.conferences.entities.Talk;
import edu.yuriiknowsjava.xpinjection.conferences.entities.TalkType;

public class TalkMapper {
    public static TalkDto toDto(Talk talk) {
        return new TalkDto(
                talk.getId(),
                talk.getTitle(),
                talk.getDescription(),
                TalkType.LECTURE
        );
    }
}
