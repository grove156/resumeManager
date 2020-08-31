package com.projects.resumeManager.dto.request;


import javax.validation.constraints.NotEmpty;

public class LoginRequest {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
}
