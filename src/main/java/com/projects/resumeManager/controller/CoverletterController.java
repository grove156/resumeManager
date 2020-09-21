package com.projects.resumeManager.controller;

import com.projects.resumeManager.dto.request.CoverletterCreateRequest;
import com.projects.resumeManager.dto.response.CoverletterDetailResponse;
import com.projects.resumeManager.service.CoverletterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class CoverletterController {

    @Autowired
    CoverletterService coverletterService;

    @GetMapping("/resume/{resumeId}/coverletter/{coverletterId}")
    public CoverletterDetailResponse getCoverletter(@PathVariable(value = "resumeId") Long resumeId,
                               @PathVariable(value = "coverletterId") Long coverletterId) throws Exception {

        CoverletterDetailResponse coverletterDetailResponse= coverletterService.getCoverletter(resumeId, coverletterId);

        return coverletterDetailResponse;
    }

    @PostMapping("/resume/{resumeId}/coverletter")
    public CoverletterDetailResponse createCoverletter(@PathVariable(value = "resumeId") Long resumeId,
                                  @RequestBody CoverletterCreateRequest coverletterCreateRequest) throws Exception {

        CoverletterDetailResponse coverletterDetailResponse = coverletterService.createCoverletter(resumeId, coverletterCreateRequest);

        return coverletterDetailResponse;
    }

    @PatchMapping("/resume/{resumeId}/coverletter/{coverletterId}")
    public CoverletterDetailResponse updateCoverletter(@PathVariable(value = "resumeId") Long resumeId,
                                  @PathVariable(value = "coverletterId") Long coverletterId,
                                  @RequestBody CoverletterCreateRequest coverletterCreateRequest) throws Exception {

        CoverletterDetailResponse coverletterDetailResponse = coverletterService.updateCoverletter(resumeId, coverletterId, coverletterCreateRequest);

        return coverletterDetailResponse;
    }

    @ResponseBody
    @DeleteMapping("/resume/{resumeId}/coverletter/{coverletterId}")
    public String deleteCoverletter(@PathVariable(value = "resumeId") Long resumeId,
                                               @PathVariable(value = "coverletterId") Long coverletterId){
        coverletterService.deleteCoverletter(resumeId, coverletterId);

        return "sucess";
    }
}
