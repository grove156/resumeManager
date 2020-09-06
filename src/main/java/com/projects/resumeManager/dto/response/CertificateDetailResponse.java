package com.projects.resumeManager.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CertificateDetailResponse {

    private Long id;

    private String certificateTitle;

    private String score;

    private LocalDate issueDate;

    private String validDuration;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
