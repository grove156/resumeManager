package com.projects.resumeManager.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EducationCreateRequest {

    @NotEmpty
    private String studyAt;

    @NotEmpty
    private String major;

    @NotNull
    private LocalDate startDate;

    private LocalDate endDate;

}
