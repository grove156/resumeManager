package com.projects.resumeManager.dto.response;

import com.projects.resumeManager.domain.entity.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ResumeDetailResponse {

    private Long id;

    private String title;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private User user;

    private Photo photo;

    private List<Coverletter> coverletterList;

    private List<Experience> experienceList;

    private List<Education> educationList;

    private List<Certificate> certificateList;
}
