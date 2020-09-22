package com.projects.resumeManager.service;

import com.projects.resumeManager.domain.entity.Certificate;
import com.projects.resumeManager.domain.entity.Resume;
import com.projects.resumeManager.dto.request.CertificateCreateRequest;
import com.projects.resumeManager.dto.response.CertificateDetailResponse;
import com.projects.resumeManager.repository.CertificateRepository;
import com.projects.resumeManager.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @Transactional
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

        //3
        return certificateDetailResponse;
    }


    //1. find resume object to chain with certificate object
    //2. map certificate create request to certificate object
    //3. save
    //4. map saved certificate obejct to certificate detail response object and then return
    @Transactional
    public CertificateDetailResponse createCertificate(Long resumeId, CertificateCreateRequest certificateCreateRequest) throws Exception {
        //TODO: wring resume exception create and apply instead of Exception

        //1
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(()->new Exception());

        //2
        Certificate certificate = Certificate.builder()
                .certificateTitle(certificateCreateRequest.getCertificateTitle())
                .score(certificateCreateRequest.getScore())
                .issuedDate(certificateCreateRequest.getIssuedDate())
                .validDuration(certificateCreateRequest.getValidDuration())
                .createdAt(LocalDateTime.now())
                .resume(resume)
                .build();

        //3
        Certificate newCertificate = certificateRepository.save(certificate);

        //4
        CertificateDetailResponse certificateDetailResponse = CertificateDetailResponse.builder()
                .id(newCertificate.getId())
                .certificateTitle(newCertificate.getCertificateTitle())
                .score(newCertificate.getScore())
                .issueDate(newCertificate.getIssuedDate())
                .validDuration(newCertificate.getValidDuration())
                .createdAt(newCertificate.getCreatedAt())
                .build();

        return certificateDetailResponse;
    }

    //1. get certificate with certificate id
    //2. set found instance of certificate with instance of certificate update request
    //3. save updated certificate
    //4. map updated certificate with certificate detail response object  return certificate detail response object
    @Transactional
    public CertificateDetailResponse updateCertificate(Long resumeId,
                                                       Long certificateId,
                                                       CertificateCreateRequest certificateCreateRequest) throws Exception {
        //TODO: writing and apply certificate not found exception

        //1
        Certificate certificate = certificateRepository.findById(certificateId).orElseThrow(()->new Exception());

        //2
        certificate.setCertificateTitle(certificateCreateRequest.getCertificateTitle());
        certificate.setScore(certificateCreateRequest.getScore());
        certificate.setIssuedDate(certificateCreateRequest.getIssuedDate());
        certificate.setValidDuration(certificateCreateRequest.getValidDuration());
        certificate.setUpdatedAt(LocalDateTime.now());

        //3
        Certificate updatedCertificate = certificateRepository.save(certificate);

        //4
        CertificateDetailResponse certificateDetailResponse = CertificateDetailResponse.builder()
                .id(updatedCertificate.getId())
                .certificateTitle(updatedCertificate.getCertificateTitle())
                .score(updatedCertificate.getScore())
                .issueDate(updatedCertificate.getIssuedDate())
                .validDuration(updatedCertificate.getValidDuration())
                .createdAt(updatedCertificate.getCreatedAt())
                .updatedAt(updatedCertificate.getUpdatedAt())
                .build();

        return certificateDetailResponse;
    }

    public void deleteCertificate(Long resumeId, Long certificateId) {
        certificateRepository.deleteById(certificateId);
    }

}
