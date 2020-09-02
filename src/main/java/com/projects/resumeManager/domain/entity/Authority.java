package com.projects.resumeManager.domain.entity;

import com.projects.resumeManager.domain.enums.UserRole;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole authority;
}
