package com.projects.resumeManager.controller;


import com.projects.resumeManager.dto.request.EducationCreateRequest;
import com.projects.resumeManager.dto.response.EducationDetailResponse;
import com.projects.resumeManager.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EducationContoller {

    @Autowired
    EducationService educationService;

    @GetMapping("/resume/{resumeId}/education/{educationId}")
    public void getEducation(@PathVariable(value = "resumeId") Long resumeId,
                             @PathVariable(value = "educationId") Long educationId,
                             Model model) throws Exception {

        EducationDetailResponse educationDetailResponse = educationService.getEduacation(resumeId, educationId);

        model.addAttribute("educationDetailResponse",educationDetailResponse);
    }

    @PostMapping("/resume/{resumeId}/education")
    public EducationDetailResponse createEducation(@PathVariable(value = "resumeId") Long resumeId,
                                @RequestBody EducationCreateRequest educationCreateRequest) throws Exception {

        EducationDetailResponse educationDetailResponse = educationService.createEducation(resumeId, educationCreateRequest);

        return educationDetailResponse;
    }

    @PatchMapping("/resume/{resumeId}/education/{educationId}")
    public EducationDetailResponse updateEducation(@PathVariable(value = "resumeId") Long resumeId,
                                @PathVariable(value = "educationId") Long educationId,
                                @RequestBody EducationCreateRequest educationCreateRequest) throws Exception {
        EducationDetailResponse educationDetailResponse = educationService.updateEducation(resumeId, educationId, educationCreateRequest);

        return educationDetailResponse;
    }

    @ResponseBody
    @DeleteMapping("/resume/{resumeId}/education/{educationId}")
    public String deleteEducation(@PathVariable(value = "resumeId") Long resumeId,
                                @PathVariable(value = "educationId") Long educationId){
        educationService.deleteEducation(resumeId, educationId);

        return "success";
    }


}
