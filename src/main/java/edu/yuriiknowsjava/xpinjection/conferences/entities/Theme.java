package edu.yuriiknowsjava.xpinjection.conferences.entities;

import java.math.BigInteger;

import org.springframework.data.relational.core.mapping.Table;

@Table("themes")
public record Theme(
        BigInteger id,
        String tag,
        String description) {
}
