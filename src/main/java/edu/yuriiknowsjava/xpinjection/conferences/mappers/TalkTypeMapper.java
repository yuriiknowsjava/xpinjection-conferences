package edu.yuriiknowsjava.xpinjection.conferences.mappers;

import edu.yuriiknowsjava.xpinjection.conferences.dto.TalkTypeDto;
import edu.yuriiknowsjava.xpinjection.conferences.entities.TalkType;

public class TalkTypeMapper {

    public static TalkTypeDto toDto(TalkType talkType) {
        return switch (talkType) {
            case LECTURE -> TalkTypeDto.LECTURE;
            case WORKSHOP -> TalkTypeDto.WORKSHOP;
            case MASTERCLASS -> TalkTypeDto.MASTERCLASS;
        };
    }

    public static TalkType fromDto(TalkTypeDto talkTypeDto) {
        return switch (talkTypeDto) {
            case LECTURE -> TalkType.LECTURE;
            case WORKSHOP -> TalkType.WORKSHOP;
            case MASTERCLASS -> TalkType.MASTERCLASS;
        };
    }
}
