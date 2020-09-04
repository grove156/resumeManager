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
@Setter
@Getter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String email;

    private String password;

    @NotEmpty
    private String name;

    private LocalDate dateOfBirth;

    private String phoneNumber;

    @NotNull
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private boolean enable;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Resume> resumeList;
}
