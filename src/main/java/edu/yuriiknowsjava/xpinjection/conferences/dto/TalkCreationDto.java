package edu.yuriiknowsjava.xpinjection.conferences.dto;

import static edu.yuriiknowsjava.xpinjection.conferences.dto.TalkBeanValidationMessages.TALK_TITLE_CANNOT_BE_BLANK;
import static edu.yuriiknowsjava.xpinjection.conferences.dto.TalkBeanValidationMessages.TALK_TYPE_CANNOT_BE_NULL;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TalkCreationDto(
        @NotBlank(message = TALK_TITLE_CANNOT_BE_BLANK) String title,
        String description,
        @NotNull(message = TALK_TYPE_CANNOT_BE_NULL) TalkTypeDto talkType
) {
    public TalkCreationDto {
        description = description == null ? "" : description.strip();
    }
}
