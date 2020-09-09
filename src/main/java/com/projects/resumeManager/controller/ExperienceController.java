package com.projects.resumeManager.controller;

import com.projects.resumeManager.dto.request.ExperienceCreateRequest;
import com.projects.resumeManager.dto.response.ExperienceDetailResponse;
import com.projects.resumeManager.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class ExperienceController {

    @Autowired
    ExperienceService experienceService;

    @GetMapping("/resume/{resumeId}/experience/{experienceId}")
    public ExperienceDetailResponse getExperience(@PathVariable Long resumeId,
                              @PathVariable Long experienceId) throws Exception {

        ExperienceDetailResponse experienceDetailResponse = experienceService.getExperience(resumeId, experienceId);

        return experienceDetailResponse;
    }

    @PostMapping("/resume/{resumeId}/experience")
    public ExperienceDetailResponse createExperience(@PathVariable Long resumeId,
                                 @RequestBody ExperienceCreateRequest experienceCreateRequest) throws Exception {
        ExperienceDetailResponse experienceDetailResponse = experienceService.createExperience(resumeId, experienceCreateRequest);

        return experienceDetailResponse;
    }

    @PatchMapping("/resume/{resumeId}/experience/{experienceId}")
    public ExperienceDetailResponse updateExperience(@PathVariable Long resumeId,
                                 @PathVariable Long experienceId,
                                 @RequestBody ExperienceCreateRequest experienceCreateRequest) throws Exception {

        ExperienceDetailResponse experienceDetailResponse = experienceService.updateExperience(resumeId, experienceId, experienceCreateRequest);

        return experienceDetailResponse;
    }

    @DeleteMapping("/resume/{resumeId}/experience/{experienceId}")
    public ResponseEntity<?> deleteExperience(@PathVariable Long resumeId,
                                 @PathVariable Long experienceId){

        experienceService.deleteExperience(resumeId, experienceId);

        return ResponseEntity.status(HttpStatus.OK).body("{}");
    }
}
