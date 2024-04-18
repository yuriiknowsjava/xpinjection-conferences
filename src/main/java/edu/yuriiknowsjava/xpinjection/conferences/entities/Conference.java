package edu.yuriiknowsjava.xpinjection.conferences.entities;

import java.math.BigInteger;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OrderBy;
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
@NamedEntityGraph(
        name = "Conference.themes",
        attributeNodes = @NamedAttributeNode("themes")
)
@Entity(name = "conferences")
public class Conference {
    private static final String GENERATOR_NAME = "conferences_id_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "conferences_id_seq")
    @SequenceGenerator(name = GENERATOR_NAME, sequenceName = GENERATOR_NAME, allocationSize = 1)
    private BigInteger id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "start_date", nullable = false)
    private ZonedDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private ZonedDateTime endDate;

    @Builder.Default
    @Column(name = "description", nullable = false)
    private String description = "";

    @Builder.Default
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "conferences")
    @OrderBy("id")
    private Set<Theme> themes = new HashSet<>();
}
