package com.projects.resumeManager.controller;

import com.projects.resumeManager.dto.request.CertificateCreateRequest;
import com.projects.resumeManager.dto.response.CertificateDetailResponse;
import com.projects.resumeManager.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @GetMapping("/resume/{resumeId}/certificate/{certificateId}")
    public CertificateDetailResponse getCertificate(@PathVariable(value = "resumeId") Long resumeId,
                                                    @PathVariable(value = "certificateId") Long certificateId) throws Exception {
       CertificateDetailResponse certificateDetailResponse = certificateService.getCertificate(resumeId, certificateId);

       return certificateDetailResponse;
    }

    @PostMapping("/resume/{resumeId}/certificate")
    public CertificateDetailResponse createCertificate(@PathVariable(value = "resumeId") Long resumeId,
                                                       @Valid @RequestBody CertificateCreateRequest certificateCreateRequest) throws Exception {
        CertificateDetailResponse certificateDetailResponse = certificateService.createCertificate(resumeId, certificateCreateRequest);

        return certificateDetailResponse;
    }

    @PatchMapping("/resume/{resumeId}/certificate/{certificateId}")
    public CertificateDetailResponse updateCertificate(@PathVariable(value = "resumeId") Long resumeId,
                                                       @PathVariable(value = "certificateId") Long certificateId,
                                                       @RequestBody CertificateCreateRequest certificateCreateRequest) throws Exception {
        CertificateDetailResponse certificateDetailResponse = certificateService.updateCertificate(resumeId, certificateId, certificateCreateRequest);

        return certificateDetailResponse;
    }

    @ResponseBody
    @DeleteMapping("/resume/{resumeId}/education/{certificateId}")
    public String deleteCertificate(@PathVariable(value = "resumeId") Long resumeId,
                                               @PathVariable(value = "certificateId") Long certificateId){
        certificateService.deleteCertificate(resumeId, certificateId);

        return "success!";
    }
}
