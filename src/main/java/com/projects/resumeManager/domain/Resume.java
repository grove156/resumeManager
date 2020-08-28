package com.projects.resumeManager.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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
    private String title;

    @NotNull
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToOne
    private User user;

    @OneToOne
    private Photo photo;

    @ManyToOne
    private Coverletter coverletter;

    @ManyToOne
    private Experience experience;

    @ManyToOne
    private Education education;

    @ManyToOne
    private Certificate certificate;
}
