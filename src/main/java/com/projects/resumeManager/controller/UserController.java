package com.projects.resumeManager.controller;

import com.projects.resumeManager.domain.entity.User;
import com.projects.resumeManager.dto.request.UserCreateRequest;
import com.projects.resumeManager.dto.request.UserUpdateRequest;
import com.projects.resumeManager.dto.response.UserDetailResponse;
import com.projects.resumeManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.model.IModel;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String getRegisterPage(){
        return "register";
    }

    @PostMapping("/register")
    public String createUser(@RequestBody @Valid UserCreateRequest resource, Model model){
        User savedUser = userService.createUser(resource);
        model.addAttribute("user", savedUser);
        return "login";
    }

    @GetMapping("/user/{id}")
    public String getUserDetail(@PathVariable Long id,
                              Model model) throws Exception {
        UserDetailResponse userDetail = userService.getUserDetail(id);
        model.addAttribute("userDetail",userDetail);

        return "/userUpdate";
    }

    @PatchMapping("/user/{id}")
    public String updateUserDetail(@PathVariable Long id,
                                   @RequestBody UserUpdateRequest resource,
                                   Model model) throws Exception {
        User updatedUserDetail = userService.updateUser(id, resource);
        return "dashboard";
    }
}
