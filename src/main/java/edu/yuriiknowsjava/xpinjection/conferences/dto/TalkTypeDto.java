package edu.yuriiknowsjava.xpinjection.conferences.dto;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import edu.yuriiknowsjava.xpinjection.conferences.exceptions.BusinessValidationException;

public enum TalkTypeDto {
    LECTURE,
    WORKSHOP,
    MASTERCLASS;

    @JsonCreator
    static TalkTypeDto fromString(String string) {
        if (string == null) {
            return null;
        }
        var values = Stream.of(TalkTypeDto.values()).toList();
        return values.stream()
                .filter(value -> value.name().equals(string))
                .findFirst()
                .orElseThrow(() -> new BusinessValidationException("talkType should be one of " + values));
    }
}
