package edu.yuriiknowsjava.xpinjection.conferences.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Entity(name = "talks")
public class Talk {
    private static final String GENERATOR_NAME = "talks_id_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GENERATOR_NAME)
    @SequenceGenerator(name = GENERATOR_NAME, sequenceName = GENERATOR_NAME, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Builder.Default
    @Column(name = "description")
    private String description = "";

    @Enumerated(EnumType.STRING)
    @Column(name = "talk_type", nullable = false)
    private TalkType talkType;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "conference_id")
    private Conference conference;
}
