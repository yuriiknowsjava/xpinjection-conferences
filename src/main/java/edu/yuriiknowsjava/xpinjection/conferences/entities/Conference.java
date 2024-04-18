package edu.yuriiknowsjava.xpinjection.conferences.entities;

import java.math.BigInteger;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "conferences")
public class Conference {
    private static final String GENERATOR_NAME = "conferences_id_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "conferences_id_seq")
    @SequenceGenerator(name = GENERATOR_NAME, sequenceName = GENERATOR_NAME, allocationSize = 1)
    private BigInteger id;
    private String name;

    @Column(name = "start_date")
    private ZonedDateTime startDate;

    @Column(name = "end_date")
    private ZonedDateTime endDate;

    @Builder.Default
    private String description = "";

    @ManyToMany
    @JoinTable(name = "conferences_themes")
    private List<Theme> themes = new ArrayList<>();
}
