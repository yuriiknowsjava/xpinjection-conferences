package edu.yuriiknowsjava.xpinjection.conferences.dto;

import java.util.List;

public record ApiError(List<String> messages) {
    public ApiError(String message) {
        this(List.of(message));
    }
}
