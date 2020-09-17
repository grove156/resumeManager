package com.projects.resumeManager.controller;

import com.projects.resumeManager.domain.entity.User;
import com.projects.resumeManager.dto.SessionUser;
import com.projects.resumeManager.dto.response.ResumeDetailResponse;
import com.projects.resumeManager.exceptionHandle.exceptions.ResumeListNoExistException;
import com.projects.resumeManager.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SessionController {

    @Autowired
    HttpSession httpSession;

    @Autowired
    ResumeService resumeService;

    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterPage(){
        return "register";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) throws Exception, ResumeListNoExistException {
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        if(sessionUser == null){
            //TODO: replace with SessionExpiredException
            throw new Exception();
        }

        List<ResumeDetailResponse> resumeList = resumeService.getResumeList(sessionUser.getId());
        model.addAttribute("resumeList",resumeList);
        model.addAttribute("user", sessionUser);
        return "dashboard";
    }

}
