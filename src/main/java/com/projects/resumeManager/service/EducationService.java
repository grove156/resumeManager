package com.projects.resumeManager.service;

import com.projects.resumeManager.domain.entity.Education;
import com.projects.resumeManager.domain.entity.Resume;
import com.projects.resumeManager.dto.request.EducationCreateRequest;
import com.projects.resumeManager.dto.response.EducationDetailResponse;
import com.projects.resumeManager.repository.EducationRepository;
import com.projects.resumeManager.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class EducationService {

    @Autowired
    EducationRepository educationRepository;

    @Autowired
    ResumeRepository resumeRepository;

    //1. find education with education id
    //2. map education detail response object with found education object
    //3.return education detail response
    @Transactional
    public EducationDetailResponse getEduacation(Long resumeId, Long educationId) throws Exception {
        //TODO:EducationNotFound exception and replace it with Exception
        //1
        Education education = educationRepository.findById(educationId).orElseThrow(()->new Exception());

        //2
        EducationDetailResponse educationDetailResponse = EducationDetailResponse.builder()
                .id(education.getId())
                .studyAt(education.getStudyAt())
                .major(education.getMajor())
                .startDate(education.getStartDate())
                .endDate(education.getEndDate())
                .createdAt(education.getCreatedAt())
                .updatedAt(education.getUpdatedAt())
                .build();

        //3
        return educationDetailResponse;
    }

    //1. find resume object with resume id
    //2. set education instance with instance of education request
    //3. save education object
    //4. map education detail response object with saved education object
    @Transactional
    public EducationDetailResponse createEducation(Long resumeId, EducationCreateRequest educationCreateRequest) throws Exception {
        //TODO:ResumeNotFoundException and replace it
        //1
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(()->new Exception());

        //2
        Education education = Education.builder()
                .studyAt(educationCreateRequest.getStudyAt())
                .major(educationCreateRequest.getMajor())
                .startDate(educationCreateRequest.getStartDate())
                .endDate(educationCreateRequest.getEndDate())
                .createdAt(LocalDateTime.now())
                .resume(resume)
                .build();

        //3
        Education savedEducation = educationRepository.save(education);

        //4
        EducationDetailResponse educationDetailResponse = EducationDetailResponse.builder()
                .id(savedEducation.getId())
                .studyAt(savedEducation.getStudyAt())
                .major(savedEducation.getMajor())
                .startDate(savedEducation.getStartDate())
                .endDate(savedEducation.getEndDate())
                .createdAt(savedEducation.getCreatedAt())
                .updatedAt(savedEducation.getUpdatedAt())
                .build();

        return educationDetailResponse;
    }

    //1. find education with education id
    //2. replace found education object instance with education reqeusted instance
    //3. save updated education object
    //4. map education detail response with saved education object then return
    @Transactional
    public EducationDetailResponse updateEducation(Long resumeId, Long educationId, EducationCreateRequest educationCreateRequest) throws Exception {
        //TODO:EducationNotFound exception and replace it with Exception
        //1
        Education education = educationRepository.findById(educationId).orElseThrow(()->new Exception());

        //2
        education.setStudyAt(educationCreateRequest.getStudyAt());
        education.setMajor(educationCreateRequest.getMajor());
        education.setStartDate(educationCreateRequest.getStartDate());
        education.setEndDate(educationCreateRequest.getEndDate());

        //3
        Education updatedEducation = educationRepository.save(education);

        //4
        EducationDetailResponse educationDetailResponse = EducationDetailResponse.builder()
                .id(updatedEducation.getId())
                .studyAt(updatedEducation.getStudyAt())
                .major(updatedEducation.getMajor())
                .startDate(updatedEducation.getStartDate())
                .endDate(updatedEducation.getEndDate())
                .createdAt(updatedEducation.getCreatedAt())
                .updatedAt(updatedEducation.getUpdatedAt())
                .build();

        return educationDetailResponse;
    }

    public void deleteEducation(Long resumeId, Long educationId) {
        educationRepository.deleteById(educationId);
    }

}
