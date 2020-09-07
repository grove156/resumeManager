package com.projects.resumeManager.controller;

import com.projects.resumeManager.dto.request.ExperienceCreateRequest;
import com.projects.resumeManager.dto.response.ExperienceDetailResponse;
import com.projects.resumeManager.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ExperienceController {

    @Autowired
    ExperienceService experienceService;

    @GetMapping("/resume/{resumeId}/experience/{experienceId}")
    public void getExperience(@PathVariable Long resumeId,
                              @PathVariable Long experienceId,
                              Model model) throws Exception {
        ExperienceDetailResponse experienceDetailResponse = experienceService.getExperience(resumeId, experienceId);

        model.addAttribute("experienceDetailResponse", experienceDetailResponse);
    }

    @PostMapping("/resume/{resumeId}/experience")
    public void createExperience(@PathVariable Long resumeId,
                                 @RequestBody ExperienceCreateRequest experienceCreateRequest,
                                 Model model) throws Exception {
        ExperienceDetailResponse experienceDetailResponse = experienceService.createExperience(resumeId, experienceCreateRequest);

        model.addAttribute("experienceDetailResponse", experienceDetailResponse);
    }

    @PatchMapping("/resume/{resumeId}/experience/{experienceId}")
    public void updateExperience(@PathVariable Long resumeId,
                                 @PathVariable Long experienceId,
                                 @RequestBody ExperienceCreateRequest experienceCreateRequest,
                                 Model model) throws Exception {
        ExperienceDetailResponse experienceDetailResponse = experienceService.updateExperience(resumeId, experienceId, experienceCreateRequest);
    }

    @DeleteMapping("/resume/{resumeId}/experience/{experienceId}")
    public void deleteExperience(@PathVariable Long resumeId,
                                 @PathVariable Long experienceId){
        experienceService.deleteExperience(resumeId, experienceId);
    }
}
