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

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ExperienceCreateRequest {

    @NotEmpty
    private String workAt;

    @NotEmpty
    private String position;

    @NotEmpty
    private String role;

    @NotNull
    private LocalDate startDate;

    private LocalDate endDate;

}
