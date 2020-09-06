package com.projects.resumeManager.controller;

import com.projects.resumeManager.dto.request.CertificateCreateRequest;
import com.projects.resumeManager.dto.response.CertificateDetailResponse;
import com.projects.resumeManager.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @GetMapping("/resume/{resumeId}/certificate/{certificateId}")
    public void getCertificate(@PathVariable(value = "resumeId") Long resumeId,
                               @PathVariable(value = "certificateId") Long certificateId,
                               Model model) throws Exception {
       CertificateDetailResponse certificateDetailResponse = certificateService.getCertificate(resumeId, certificateId);

       model.addAttribute("certificateDetail", certificateDetailResponse);
    }

    @PostMapping("/resume/{resumeId}/certificate")
    public void createCertificate(@PathVariable(value = "resumeId") Long resumeId,
                                  @Valid @RequestBody CertificateCreateRequest certificateCreateRequest) throws Exception {
        CertificateDetailResponse certificateDetailResponse = certificateService.createCertificate(resumeId, certificateCreateRequest);
    }

    @PatchMapping("/resume/{resumeId}/certificate/{certificateId}")
    public void updateCertificate(@PathVariable(value = "resumeId") Long resumeId,
                                  @PathVariable(value = "certificateId") Long certificateId,
                                  @RequestBody CertificateCreateRequest certificateCreateRequest) throws Exception {
        CertificateDetailResponse certificateDetailResponse = certificateService.updateCertificate(resumeId, certificateId, certificateCreateRequest);
    }

    @DeleteMapping("/resume/{resumeId}/education/{certificateId}")
    public void deleteCertificate(@PathVariable(value = "resumeId") Long resumeId,
                                  @PathVariable(value = "certificateId") Long certificateId){
        certificateService.deleteCertificate(resumeId, certificateId);
    }
}
