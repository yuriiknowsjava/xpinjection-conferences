package edu.yuriiknowsjava.xpinjection.conferences.dto;

import java.math.BigInteger;

public record ThemeDto(
        BigInteger id,
        String name,
        String description
) {
}
