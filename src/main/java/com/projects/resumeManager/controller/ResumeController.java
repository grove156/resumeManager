package com.projects.resumeManager.controller;

import com.projects.resumeManager.domain.AuthUser;
import com.projects.resumeManager.domain.entity.Resume;
import com.projects.resumeManager.domain.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class ResumeController {

    Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();
    AuthUser user = (AuthUser) authentication.getPrincipal();

    @GetMapping("/dashboard")
    public String dashboard(Model model){

        model.addAttribute("user",user);
        return "dashboard";
    }
}
