package com.projects.resumeManager.controller;

import com.projects.resumeManager.dto.request.CoverletterCreateRequest;
import com.projects.resumeManager.dto.response.CoverletterDetailResponse;
import com.projects.resumeManager.service.CoverletterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CoverletterController {

    @Autowired
    CoverletterService coverletterService;

    @GetMapping("/resume/{resumeId}/coverletter/{coverletterId}")
    public void getCoverletter(@PathVariable(value = "resumeId") Long resumeId,
                               @PathVariable(value = "coverletterId") Long coverletterId,
                               Model model) throws Exception {

        CoverletterDetailResponse coverletterDetailResponse= coverletterService.getCoverletter(resumeId, coverletterId);

        model.addAttribute("coverletterDetailResponse",coverletterDetailResponse);
    }

    @PostMapping("/resume/{resumeId}/coverletter")
    public void createCoverletter(@PathVariable(value = "resumeId") Long resumeId,
                                  @RequestBody CoverletterCreateRequest coverletterCreateRequest,
                                  Model model) throws Exception {

        CoverletterDetailResponse coverletterDetailResponse = coverletterService.createCoverletter(resumeId, coverletterCreateRequest);

        model.addAttribute("coverletterDetailResponse", coverletterDetailResponse);
    }

    @PatchMapping("/resume/{resumeId}/coverletter/{coverletterId}")
    public void updateCoverletter(@PathVariable(value = "resumeId") Long resumeId,
                                  @PathVariable(value = "coverletterId") Long coverletterId,
                                  @RequestBody CoverletterCreateRequest coverletterCreateRequest,
                                  Model model) throws Exception {

        CoverletterDetailResponse coverletterDetailResponse = coverletterService.updateCoverletter(resumeId, coverletterId, coverletterCreateRequest);

        model.addAttribute("coverletterDetailResponse", coverletterDetailResponse);
    }

    @DeleteMapping("/resume/{resumeId}/coverletter/{coverletterId}")
    public void deleteCoverletter(@PathVariable(value = "resumeId") Long resumeId,
                                  @PathVariable(value = "coverletterId") Long coverletterId){
        coverletterService.deleteCoverletter(resumeId, coverletterId);
    }
}
