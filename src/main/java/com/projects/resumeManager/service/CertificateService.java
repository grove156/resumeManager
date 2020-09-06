package com.projects.resumeManager.service;

import com.projects.resumeManager.domain.entity.Certificate;
import com.projects.resumeManager.dto.request.CertificateCreateRequest;
import com.projects.resumeManager.dto.response.CertificateDetailResponse;
import com.projects.resumeManager.repository.CertificateRepository;
import com.projects.resumeManager.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CertificateService {

    @Autowired
    private CertificateRepository certificateRepository;

    @Autowired
    private ResumeRepository resumeRepository;

    //1. find certificate details using certificate id
    //2. mapping certificate details to certificate detail response object
    //3. return certificate response object
    public CertificateDetailResponse getCertificate(Long resumeId, Long certificateId) throws Exception {
        //TODO: create and apply certificate not exist exception
        //find certificate with certificate id
        Certificate certificate = certificateRepository.findById(certificateId).orElseThrow(()->new Exception());

        //mapping certificate with response object
        CertificateDetailResponse certificateDetailResponse = CertificateDetailResponse.builder()
                .id(certificate.getId())
                .certificateTitle(certificate.getCertificateTitle())
                .score(certificate.getScore())
                .issueDate(certificate.getIssuedDate())
                .validDuration(certificate.getValidDuration())
                .createdAt(certificate.getCreatedAt())
                .updatedAt(certificate.getUpdatedAt())
                .build();

        return certificateDetailResponse;
    }

    public void createCertificate(Long resumeId, CertificateCreateRequest certificateCreateRequest){
        Certificate certificate =Certificate.builder()
                .certificateTitle()
                .score()
                .issuedDate()
                .validDuration()
                .
                .createdAt()
                .build();
    }
}
