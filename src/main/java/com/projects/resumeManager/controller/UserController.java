package com.projects.resumeManager.controller;

import com.projects.resumeManager.domain.entity.User;
import com.projects.resumeManager.dto.request.UserCreateRequest;
import com.projects.resumeManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.model.IModel;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String getRegisterPage(){
        return "register";
    }

    @PostMapping("/register")
    public String createUser(@RequestBody UserCreateRequest resource, Model model){
        User savedUser = userService.createUser(resource);
        model.addAttribute("user", savedUser);
        return "login";
    }
}
