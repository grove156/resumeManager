package com.projects.resumeManager.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Photo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String photoTitle;

    @NotEmpty
    private String fileSize;

    @NotEmpty
    private String url;

    @NotNull
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "photo")
    private Resume resume;

    
}
