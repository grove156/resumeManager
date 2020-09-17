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
        ResumeDetailResponse resumeDetailResponse = resumeService.getResumeDetail(resumeId);

        model.addAttribute("resumeDetailResponse", resumeDetailResponse);

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
    public String resumeCreatePage(){

        return "createResume";
    }

    @PostMapping("/{userId}/resume")
    public void createResume(@PathVariable(value = "userId") Long userId,
                             @RequestBody String title,
                             Model model){

        ResumeDetailResponse resumeDetailResponse = resumeService.createResume(userId, title);

        model.addAttribute("resumeDetailResponse", resumeDetailResponse);
    }

    @PatchMapping("/{userId}/resume/{resumeId}")
    public void updateResume(@PathVariable(value = "userId") Long userId,
                             @PathVariable(value = "userId") Long resumeId,
                             @RequestBody String title,
                             Model model){
        ResumeDetailResponse resumeDetailResponse = resumeService.updateResume(userId, resumeId, title);

        model.addAttribute("resumeDetailResponse", resumeDetailResponse);
    }

    @DeleteMapping("/{userId}/resume/{resumeId}")
    public void deleteResume(@PathVariable(value = "userId") Long userId,
                             @PathVariable(value = "userId") Long resumeId){
        resumeService.deleteResume(userId, resumeId);
    }

}
