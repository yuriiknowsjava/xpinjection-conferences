package edu.yuriiknowsjava.xpinjection.conferences.dto;

import java.util.List;

public record ApiErrorDto(List<String> messages) {
    public ApiErrorDto(String message) {
        this(List.of(message));
    }
}
