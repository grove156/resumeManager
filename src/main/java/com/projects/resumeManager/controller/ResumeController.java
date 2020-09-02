package com.projects.resumeManager.controller;

import com.projects.resumeManager.domain.entity.Resume;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ResumeController {

    @GetMapping("/dashboard")
    public String dashboard(){
        return "<h1>Welcome</h1>";
    }
}
