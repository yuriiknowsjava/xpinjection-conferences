package edu.yuriiknowsjava.xpinjection.conferences.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "talks")
public class Talk {
    @Id
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

    @ManyToOne
    @JoinColumn(name = "conference_id")
    private Conference conference;
}
