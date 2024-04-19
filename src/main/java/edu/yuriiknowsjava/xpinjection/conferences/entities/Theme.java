package edu.yuriiknowsjava.xpinjection.conferences.entities;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "themes")
public class Theme {
    private static final String GENERATOR_NAME = "themes_id_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GENERATOR_NAME)
    @SequenceGenerator(name = GENERATOR_NAME, sequenceName = GENERATOR_NAME, allocationSize = 1)
    private BigInteger id;

    @Column(name = "tag", nullable = false, unique = true)
    private String tag;

    @Builder.Default
    @Column(name = "description", nullable = false)
    private String description = "";

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @OrderBy("name")
    @JoinTable(name = "conferences_themes",
            joinColumns = @JoinColumn(name = "theme_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "conference_id", referencedColumnName = "id"))
    private Set<Conference> conferences = new HashSet<>();
}
