package com.projects.resumeManager.controller;

import com.projects.resumeManager.domain.AuthUser;
import com.projects.resumeManager.domain.entity.Resume;
import com.projects.resumeManager.domain.entity.User;
import com.projects.resumeManager.dto.SessionUser;
import com.projects.resumeManager.dto.response.ResumeDetailResponse;
import com.projects.resumeManager.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;

@Controller
public class ResumeController {

    @Autowired
    HttpSession httpSession;

    @Autowired
    ResumeService resumeService;

    //for update
    @GetMapping("/resume/{resumeId}")
    public String getResumeDetailForUpdate(@PathVariable Long resumeId,
                                         Model model) throws Exception {

        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        if(sessionUser == null){
            //TODO: replace with SessionExpiredException
            throw new Exception();
        }

        ResumeDetailResponse resumeDetailResponse = resumeService.getResumeDetail(resumeId);

        model.addAttribute("resume", resumeDetailResponse);
        model.addAttribute("user", sessionUser);

        return "updateResume";
    }

    //for display
    @GetMapping("/resume/{resumeId}/{templateId}")
    public String getResume(@PathVariable Long resumeId,
                          @PathVariable Long templateId,
                          Model model) throws Exception {
        ResumeDetailResponse resumeDetailResponse = resumeService.getResumeDetail(resumeId);

        model.addAttribute("resumeDetailResponse", resumeDetailResponse);

        return "";//TODO:create "/resume/{resumeId}/{templateID}" template with resume detail data
    }

    @GetMapping("/{userId}/resume")
    public String resumeCreatePage(Model model) throws Exception {

        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        if(sessionUser == null){
            //TODO: replace with SessionExpiredException
            throw new Exception();
        }

        model.addAttribute("user", sessionUser);
        return "createResume";
    }

    @ResponseBody
    @PostMapping("/{userId}/resume")
    public ResumeDetailResponse createResume(@PathVariable(value = "userId") Long userId,
                             @RequestBody String title){

        ResumeDetailResponse resumeDetailResponse = resumeService.createResume(userId, title);

        return resumeDetailResponse;
    }

    @PatchMapping("/{userId}/resume/{resumeId}")
    public void updateResume(@PathVariable(value = "userId") Long userId,
                             @PathVariable(value = "userId") Long resumeId,
                             @RequestBody String title,
                             Model model){
        ResumeDetailResponse resumeDetailResponse = resumeService.updateResume(userId, resumeId, title);

        model.addAttribute("resumeDetailResponse", resumeDetailResponse);
    }

    @ResponseBody
    @DeleteMapping("/{userId}/resume/{resumeId}")
    public String deleteResume(@PathVariable(value = "userId") Long userId,
                             @PathVariable(value = "resumeId") Long resumeId){
        resumeService.deleteResume(userId, resumeId);

        return "success!";
    }

}
