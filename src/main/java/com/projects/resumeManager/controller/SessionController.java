package com.projects.resumeManager.controller;

import com.projects.resumeManager.dto.request.LoginRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

    @PostMapping("/login")
    public void login(){

    }

    @PostMapping("/register")
    public void register(){

    }
}
