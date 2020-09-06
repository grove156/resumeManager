package com.projects.resumeManager.service;

import com.projects.resumeManager.domain.entity.Coverletter;
import com.projects.resumeManager.domain.entity.Resume;
import com.projects.resumeManager.dto.request.CoverletterCreateRequest;
import com.projects.resumeManager.dto.response.CoverletterDetailResponse;
import com.projects.resumeManager.repository.CoverletterRepository;
import com.projects.resumeManager.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CoverletterService {

    @Autowired
    private CoverletterRepository coverletterRepository;

    @Autowired
    private ResumeRepository resumeRepository;


    //1. find coverletter with coverletterId
    //2. map found coverletter with coverletterDetailResponse object
    //3. return coverletterDetailResponse
    public CoverletterDetailResponse getCoverletter(Long resumeId, Long coverletterId) throws Exception {
        //TODO: create exception for coverletterNotFound
        //1
        Coverletter coverletter = coverletterRepository.findById(coverletterId).orElseThrow(()-> new Exception());

        //2
        CoverletterDetailResponse coverletterDetailResponse = CoverletterDetailResponse.builder()
                .id(coverletter.getId())
                .title(coverletter.getTitle())
                .content(coverletter.getContent())
                .createdAt(coverletter.getCreatedAt())
                .updatedAt(coverletter.getUpdatedAt())
                .build();

        //3
        return coverletterDetailResponse;
    }

    //1. get resume object with resumeId (for the instance of coverletter object)
    //2. map coverletter create request object with coverletter object
    //3. save
    //4. map coverletter detail response object with coverletter then return
    public CoverletterDetailResponse createCoverletter(Long resumeId,
                                                       CoverletterCreateRequest coverletterCreateRequest) throws Exception {
        //TODO: create exception for resumeNotFound
        //1
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(()->new Exception());

        //2
        Coverletter coverletter = Coverletter.builder()
                .title(coverletterCreateRequest.getTitle())
                .content(coverletterCreateRequest.getContent())
                .createdAt(LocalDateTime.now())
                .resume(resume)
                .build();

        //3
        Coverletter newCoverletter = coverletterRepository.save(coverletter);

        //4
        CoverletterDetailResponse coverletterDetailResponse = CoverletterDetailResponse.builder()
                .id(newCoverletter.getId())
                .title(newCoverletter.getTitle())
                .content(newCoverletter.getContent())
                .createdAt(newCoverletter.getCreatedAt())
                .updatedAt(newCoverletter.getUpdatedAt())
                .build();

        return coverletterDetailResponse;
    }


    //1. find coverletter object by coverletterId
    //2. set coverletter instance with instance of coverletterCreateRequest
    //3. save coverletter
    //4. map coverletterDetailResponse with coverletter and return;
    public CoverletterDetailResponse updateCoverletter(Long resumeId, Long coverletterId, CoverletterCreateRequest coverletterCreateRequest) throws Exception {

        //TODO: create exception for coverletterNotFound
        //1
        Coverletter coverletter = coverletterRepository.findById(coverletterId).orElseThrow(()->new Exception());

        //2
        coverletter.setTitle(coverletterCreateRequest.getTitle());
        coverletter.setContent(coverletterCreateRequest.getContent());

        //3
        Coverletter savedCoverletter = coverletterRepository.save(coverletter);

        //4
        CoverletterDetailResponse coverletterDetailResponse = CoverletterDetailResponse.builder()
                .id(savedCoverletter.getId())
                .title(savedCoverletter.getTitle())
                .content(savedCoverletter.getContent())
                .createdAt(savedCoverletter.getCreatedAt())
                .updatedAt(savedCoverletter.getUpdatedAt())
                .build();

        return coverletterDetailResponse;
    }

    public void deleteCoverletter(Long resumeId, Long coverletterId) {
        coverletterRepository.deleteById(coverletterId);
    }
}
