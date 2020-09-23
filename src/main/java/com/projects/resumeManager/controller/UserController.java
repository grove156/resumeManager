package com.projects.resumeManager.controller;

import com.projects.resumeManager.domain.entity.User;
import com.projects.resumeManager.dto.SessionUser;
import com.projects.resumeManager.dto.request.UserCreateRequest;
import com.projects.resumeManager.dto.request.UserUpdateRequest;
import com.projects.resumeManager.dto.response.UserDetailResponse;
import com.projects.resumeManager.service.UserService;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.model.IModel;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    UserService userService;


    @Autowired
    HttpSession httpSession;

    @PostMapping("/register")
    @ResponseBody
    public User createUser(@RequestBody UserCreateRequest resource ){
        System.out.println(resource);
        User savedUser = userService.createUser(resource);
//        model.addAttribute("user", savedUser);
        return savedUser;
    }

    //for update
    @GetMapping("/user/{id}")
    public String getUserDetail(@PathVariable Long id,
                              Model model) throws Exception {
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        if(sessionUser == null){
            //TODO: replace with SessionExpiredException
            throw new Exception();
        }

        UserDetailResponse userDetail = userService.getUserDetail(id);
        model.addAttribute("userDetail",userDetail);
        model.addAttribute("user", sessionUser);
        return "userUpdate";
    }

    @ResponseBody
    @PatchMapping("/user/{id}")
    public String updateUserDetail(@PathVariable Long id,
                                   @RequestBody UserUpdateRequest resource) throws Exception {
        User updatedUserDetail = userService.updateUser(id, resource);
        return "success!";
    }
}
