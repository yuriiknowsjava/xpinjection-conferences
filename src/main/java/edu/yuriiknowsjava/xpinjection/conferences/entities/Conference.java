package edu.yuriiknowsjava.xpinjection.conferences.entities;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "start_date", nullable = false)
    private ZonedDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private ZonedDateTime endDate;

    @Builder.Default
    @Column(name = "description", nullable = false)
    private String description = "";

    // see more https://medium.com/@kiarash.shamaii/what-is-n-1-query-generate-problem-in-spring-data-jpa-and-how-to-solve-it-2f3b9f1a7a0b
    // https://stackoverflow.com/questions/70567098/manytomany-n1-problem-all-right-side-not-being-returned
    @Builder.Default
    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "conferences")
    @OrderBy("tag")
    private Set<Theme> themes = new HashSet<>();

    @OneToMany(mappedBy = "conference")
    private Set<Talk> talks = new HashSet<>();

    public void addTheme(Theme theme) {
        themes.add(theme);
        theme.getConferences().add(this);
    }
}
