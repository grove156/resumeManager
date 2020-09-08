package com.projects.resumeManager.service;

import com.projects.resumeManager.domain.entity.Resume;
import com.projects.resumeManager.domain.entity.User;
import com.projects.resumeManager.dto.response.ResumeDetailResponse;
import com.projects.resumeManager.repository.ResumeRepository;
import com.projects.resumeManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResumeService {

    @Autowired
    ResumeRepository resumeRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public List<ResumeDetailResponse> getResumeList(Long userId) throws Exception {
        //TODO:proper userNotFound exception and apply
        User user = userRepository.findById(userId).orElseThrow(()->new Exception());

        List<Resume> resumeList = resumeRepository.findByUser(user);
        //TODO: proper ResumeListNotFound exception
        if(resumeList.get(0).getId()==null){
            throw new Exception();
        }

        List<ResumeDetailResponse> resumeDetailResponseList= new ArrayList();
        for(Resume resume : resumeList){
            ResumeDetailResponse resumeDetailResponse = ResumeDetailResponse.builder()
                    .id(resume.getId())
                    .title(resume.getTitle())
                    .createdAt(resume.getCreatedAt())
                    .updatedAt(resume.getUpdatedAt())
                    .user(resume.getUser())
                    .photo(resume.getPhoto())
                    .certificateList(resume.getCertificateList())
                    .coverletterList(resume.getCoverletterList())
                    .educationList(resume.getEducationList())
                    .experienceList(resume.getExperienceList())
                    .build();
            resumeDetailResponseList.add(resumeDetailResponse);
        }

        return resumeDetailResponseList;
    }

    @Transactional
    public ResumeDetailResponse getResumeDetail(Long resumeId) throws Exception {
        //TODO: resumeNotFound exception
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(()->new Exception());

        ResumeDetailResponse resumeDetailResponse = ResumeDetailResponse.builder()
                .id(resume.getId())
                .title(resume.getTitle())
                .createdAt(resume.getCreatedAt())
                .updatedAt(resume.getUpdatedAt())
                .user(resume.getUser())
                .photo(resume.getPhoto())
                .experienceList(resume.getExperienceList())
                .educationList(resume.getEducationList())
                .coverletterList(resume.getCoverletterList())
                .certificateList(resume.getCertificateList())
                .build();

        return resumeDetailResponse;
    }
}
