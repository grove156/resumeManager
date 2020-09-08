package com.projects.resumeManager.service;

import com.projects.resumeManager.domain.entity.Photo;
import com.projects.resumeManager.domain.entity.Resume;
import com.projects.resumeManager.domain.entity.User;
import com.projects.resumeManager.dto.PhotoCreateRequest;
import com.projects.resumeManager.dto.response.PhotoDetailResponse;
import com.projects.resumeManager.repository.PhotoRepository;
import com.projects.resumeManager.repository.ResumeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class PhotoService {

    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    ResumeRepository resumeRepository;

    //1. let uploaded file saved in server folder (DATABASE x)
    //2. get details from saved photo(in the server folder) object
    //3. save  to database
    //4. map photo detail response data with saved data from database
    @Transactional
    public PhotoDetailResponse getPhoto(Long resumeId){
        //TODO:proper exception handling
        Resume resume = resumeRepository.findById(resumeId).orElseThrow();

        Photo photo = photoRepository.findByResume(resume);

        PhotoDetailResponse photoDetailResponse = PhotoDetailResponse.builder()
                .id(photo.getId())
                .photoTitle(photo.getPhotoTitle())
                .url(photo.getUrl())
                .fileSize(photo.getFileSize())
                .build();

        return photoDetailResponse;
    }

    @Transactional
    public PhotoDetailResponse createPhoto(Long resumeId, MultipartFile uploadFile){
        //TODO: proper exception
        Resume resume = resumeRepository.findById(resumeId).orElseThrow();

        PhotoCreateRequest photoCreateRequest = photoUpload(uploadFile);

        Photo photo = Photo.builder()
                .photoTitle(photoCreateRequest.getPhotoTitle())
                .fileSize(photoCreateRequest.getFileSize())
                .url(photoCreateRequest.getUrl())
                .createdAt(photoCreateRequest.getCreatedAt())
                .resume(resume)
                .build();

        Photo savedPhoto = photoRepository.save(photo);

        PhotoDetailResponse photoDetailResponse = PhotoDetailResponse.builder()
                .id(savedPhoto.getId())
                .photoTitle(savedPhoto.getPhotoTitle())
                .fileSize(savedPhoto.getFileSize())
                .url(savedPhoto.getUrl())
                .createdAt(savedPhoto.getCreatedAt())
                .build();

        return photoDetailResponse;
    }

    @Transactional
    public PhotoDetailResponse updatePhoto(Long photoId, Long resumeId, MultipartFile uploadFile){
        PhotoCreateRequest photoCreateRequest = photoUpload(uploadFile);
        //TODO: proper exception
        Photo photo = photoRepository.findById(photoId).orElseThrow();

        photo.setPhotoTitle(photoCreateRequest.getPhotoTitle());
        photo.setFileSize(photoCreateRequest.getFileSize());
        photo.setUrl(photoCreateRequest.getUrl());
        photo.setCreatedAt(LocalDateTime.now());

        Photo savedPhoto = photoRepository.save(photo);

        PhotoDetailResponse photoDetailResponse = PhotoDetailResponse.builder()
                .id(savedPhoto.getId())
                .photoTitle(savedPhoto.getPhotoTitle())
                .fileSize(savedPhoto.getFileSize())
                .url(savedPhoto.getUrl())
                .createdAt(savedPhoto.getCreatedAt())
                .build();

        return photoDetailResponse;
    }

    //1. save uploadede files to the directory ..resources/static/ProfileImg
    //2.
    private PhotoCreateRequest photoUpload(MultipartFile uploadFile){
        //need to be changed on server
        String baseDir = "C:\\Users\\grove\\OneDrive\\Desktop\\projects\\resumeManager\\src\\main\\resources\\static\\ProfileImg";
        UUID uuid = UUID.randomUUID();
        File uploadPath = new File(baseDir);

        String uploadFileName = uploadFile.getOriginalFilename()+"_"+uuid.toString();
        String uoloadFileSize = fileSizeToMb(uploadFile.getSize());

        File saveFile = new File(uploadPath, uploadFileName);

        try{
            uploadFile.transferTo(saveFile);
            PhotoCreateRequest photoCreateRequest = PhotoCreateRequest.builder()
                    .photoTitle(uploadFileName)
                    .url("../static/ProfileImg/"+uploadFileName)
                    .fileSize(uoloadFileSize)
                    .createdAt(LocalDateTime.now())
                    .build();
            return photoCreateRequest;
        }catch(Exception e){
            //Todo:proper exception handling
            log.error(e.getMessage());
            return null;
        }
    }

    //1. function to set byte fileSize to Mb size or Kb size
    //2. take original byte filesize as a parameter
    //3.  transfer original filesize into Mb or Kb String value
    private String fileSizeToMb(Long fileSize){
        Long kb = 1024L;
        Long mb = kb*kb;
        String size;
        if(fileSize/mb>=1){
            float temp = fileSize/mb;
            size = String.format("%.2f",temp) + "Mb";
        }else if(fileSize/kb>=1){
            float temp = fileSize/kb;
            size = String.format("%.2f",temp) + "Kb";
        }else{
            size = fileSize +"Byte";
        }

        return size;
    }
}
