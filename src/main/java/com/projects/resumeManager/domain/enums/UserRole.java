package com.projects.resumeManager.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
    USER(1,"USER"),
    ADMIN(2,"ADMIN");

    private Integer id;
    private String role;

}
