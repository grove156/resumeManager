package com.projects.resumeManager.dto.response;

import com.projects.resumeManager.domain.entity.Resume;
import lombok.*;

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
@Getter
public class PhotoDetailResponse {

    private Long id;

    private String photoTitle;

    private String fileSize;

    private String url;

    private LocalDateTime createdAt;

}
