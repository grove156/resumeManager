package com.projects.resumeManager.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
    ROLE_USER(1,"USER"),
    ROLE_ADMIN(2,"ADMIN");

    private Integer id;
    private String role;

}
