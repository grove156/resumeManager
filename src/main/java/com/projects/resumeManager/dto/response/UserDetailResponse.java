package com.projects.resumeManager.dto.response;

import com.projects.resumeManager.domain.entity.Resume;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailResponse {

    private Long id;

    private String email;

    private String name;

    private LocalDate dateOfBirth;

    private String phoneNumber;

}
