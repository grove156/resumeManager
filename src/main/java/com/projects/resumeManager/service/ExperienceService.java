package com.projects.resumeManager.service;

import com.projects.resumeManager.domain.entity.Experience;
import com.projects.resumeManager.domain.entity.Resume;
import com.projects.resumeManager.dto.request.ExperienceCreateRequest;
import com.projects.resumeManager.dto.response.ExperienceDetailResponse;
import com.projects.resumeManager.repository.ExperienceRepository;
import com.projects.resumeManager.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ExperienceService {

    @Autowired
    ExperienceRepository experienceRepository;

    @Autowired
    ResumeRepository resumeRepository;

    //1. find experience with experience id
    //2. map response object with experience object
    //3. return
    public ExperienceDetailResponse getExperience(Long resumeId, Long experienceId) throws Exception {
        //TODO: experienceNotFound exception create and apply

        //1
        Experience experience = experienceRepository.findById(experienceId).orElseThrow(()->new Exception());

        //2
        ExperienceDetailResponse experienceDetailResponse = ExperienceDetailResponse.builder()
                .id(experience.getId())
                .workAt(experience.getWorkAt())
                .position(experience.getPosition())
                .role(experience.getRole())
                .startDate(experience.getStartDate())
                .endDate(experience.getEndDate())
                .createdAt(experience.getCreatedAt())
                .updatedAt(experience.getUpdatedAt())
                .build();

        //3
        return experienceDetailResponse;
    }

    //1. find resume object
    //2. map experience object with request object
    //3. save experience object
    //4. map response object with saved experience object
    public ExperienceDetailResponse createExperience(Long resumeId, ExperienceCreateRequest experienceCreateRequest) throws Exception {
        //TODO: create ResumeNotFound exception and apply

        //1
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(()->new Exception());

        //2
        Experience experience = Experience.builder()
                .workAt(experienceCreateRequest.getWorkAt())
                .position(experienceCreateRequest.getPosition())
                .role(experienceCreateRequest.getRole())
                .startDate(experienceCreateRequest.getStartDate())
                .endDate(experienceCreateRequest.getEndDate())
                .createdAt(LocalDateTime.now())
                .resume(resume)
                .build();

        //3
        Experience newExperience = experienceRepository.save(experience);

        //4
        ExperienceDetailResponse experienceDetailResponse = ExperienceDetailResponse.builder()
                .id(newExperience.getId())
                .workAt(newExperience.getWorkAt())
                .position(newExperience.getPosition())
                .role(newExperience.getRole())
                .startDate(newExperience.getStartDate())
                .endDate(newExperience.getEndDate())
                .createdAt(newExperience.getCreatedAt())
                .updatedAt(newExperience.getUpdatedAt())
                .build();

        return experienceDetailResponse;
    }

    //1. find experience object with expereince id
    //2. set experience instance with instance of request object
    //3. save experience object
    //4. map response object with saved experience object
    public ExperienceDetailResponse updateExperience(Long resumeId, Long experienceId, ExperienceCreateRequest experienceCreateRequest) throws Exception {

        //1
        Experience experience = experienceRepository.findById(experienceId).orElseThrow(()->new Exception());

        //2
        experience.setWorkAt(experienceCreateRequest.getWorkAt());
        experience.setPosition(experienceCreateRequest.getPosition());
        experience.setRole(experienceCreateRequest.getRole());
        experience.setStartDate(experienceCreateRequest.getStartDate());
        experience.setEndDate(experienceCreateRequest.getEndDate());

        //3
        Experience updatedExperience = experienceRepository.save(experience);

        //4
        ExperienceDetailResponse experienceDetailResponse = ExperienceDetailResponse.builder()
                .id(updatedExperience.getId())
                .workAt(updatedExperience.getWorkAt())
                .position(updatedExperience.getPosition())
                .role(updatedExperience.getRole())
                .startDate(updatedExperience.getStartDate())
                .endDate(updatedExperience.getEndDate())
                .createdAt(updatedExperience.getCreatedAt())
                .updatedAt(updatedExperience.getUpdatedAt())
                .build();

        return experienceDetailResponse;
    }

    public void deleteExperience(Long resumeId, Long experienceId) {
        experienceRepository.deleteById(experienceId);
    }
}
