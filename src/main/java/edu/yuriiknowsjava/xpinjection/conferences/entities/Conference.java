package edu.yuriiknowsjava.xpinjection.conferences.entities;

import java.math.BigInteger;
import java.time.ZonedDateTime;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("conferences")
public record Conference(
        BigInteger id,
        String name,
        @Column("start_date") ZonedDateTime startDate,
        @Column("end_date") ZonedDateTime endDate,
        String description
) {
}
