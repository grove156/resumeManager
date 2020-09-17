package com.projects.resumeManager.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String resumeTitle;

    @NotNull
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToOne
    private User user;

    @OneToOne
    private Photo photo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "resume")
    private List<Coverletter> coverletterList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "resume")
    private List<Experience> experienceList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "resume")
    private List<Education> educationList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "resume")
    private List<Certificate> certificateList;
}
