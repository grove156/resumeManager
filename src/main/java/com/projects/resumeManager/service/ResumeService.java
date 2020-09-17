package com.projects.resumeManager.service;

import com.projects.resumeManager.domain.entity.Resume;
import com.projects.resumeManager.domain.entity.User;
import com.projects.resumeManager.dto.response.ResumeDetailResponse;
import com.projects.resumeManager.exceptionHandle.exceptions.ResumeListNoExistException;
import com.projects.resumeManager.repository.ResumeRepository;
import com.projects.resumeManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
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
        if(resumeList.isEmpty()){
            throw new ResumeListNoExistException("Resume does not exist. Create your resume first!");
        }

        List<ResumeDetailResponse> resumeDetailResponseList= new ArrayList();
        for(Resume resume : resumeList){
            ResumeDetailResponse resumeDetailResponse = ResumeDetailResponse.builder()
                    .id(resume.getId())
                    .title(resume.getResumeTitle())
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
                .title(resume.getResumeTitle())
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

    @Transactional
    public ResumeDetailResponse createResume(Long userId, String title){
        //TODO: resumeNotFound exception
        User user = userRepository.findById(userId).orElseThrow();

        Resume resume = Resume.builder()
                .resumeTitle(title)
                .user(user)
                .createdAt(LocalDateTime.now())
                .build();

        Resume savedResume = resumeRepository.save(resume);

        user.getResumeList().add(savedResume);

        userRepository.save(user);

        ResumeDetailResponse resumeDetailResponse = ResumeDetailResponse.builder()
                .id(savedResume.getId())
                .title(savedResume.getResumeTitle())
                .createdAt(savedResume.getCreatedAt())
                .user(savedResume.getUser())
                .build();

        return resumeDetailResponse;
    }

    public ResumeDetailResponse updateResume(Long userId, Long resumeId, String title) {
        //TODO: resumeNotFound exception
        Resume resume = resumeRepository.findById(resumeId).orElseThrow();

        resume.setResumeTitle(title);

        Resume savedResume = resumeRepository.save(resume);

        ResumeDetailResponse resumeDetailResponse = ResumeDetailResponse.builder()
                .id(savedResume.getId())
                .user(savedResume.getUser())
                .title(savedResume.getResumeTitle())
                .createdAt(savedResume.getCreatedAt())
                .updatedAt(savedResume.getUpdatedAt())
                .photo(savedResume.getPhoto())
                .certificateList(savedResume.getCertificateList())
                .coverletterList(savedResume.getCoverletterList())
                .educationList(savedResume.getEducationList())
                .experienceList(savedResume.getExperienceList())
                .build();

        return resumeDetailResponse;
    }


    public void deleteResume(Long userId, Long resumeId) {
        resumeRepository.deleteById(resumeId);
    }
}
