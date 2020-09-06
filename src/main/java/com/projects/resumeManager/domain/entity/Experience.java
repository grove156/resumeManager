package com.projects.resumeManager.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String workAt;

    @NotEmpty
    private String position;

    @NotEmpty
    private String role;

    @NotNull
    private LocalDate startDate;

    private LocalDate endDate;

    @NotNull
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToOne
    private Resume resume;
}

