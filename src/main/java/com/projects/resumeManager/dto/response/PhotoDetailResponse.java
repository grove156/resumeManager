package com.projects.resumeManager.dto.response;

import com.projects.resumeManager.domain.entity.Resume;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class PhotoDetailResponse {

    private Long id;

    private String photoTitle;

    private String fileSize;

    private String url;

    private LocalDateTime createdAt;

}
