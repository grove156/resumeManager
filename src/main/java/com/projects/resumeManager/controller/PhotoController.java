package com.projects.resumeManager.controller;

import com.projects.resumeManager.dto.response.PhotoDetailResponse;
import com.projects.resumeManager.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PhotoController {

    @Autowired
    PhotoService photoService;

    @GetMapping("resume/{resumeId}/photo")
    public PhotoDetailResponse getPhoto(@PathVariable(value="resumeId") Long resumeId){

        PhotoDetailResponse photoDetailResponse = photoService.getPhoto(resumeId);

        return photoDetailResponse;

    }
    @ResponseBody
    @PostMapping("resume/{resumeId}/photo")
    public PhotoDetailResponse createPhoto(@PathVariable(value="resumeId") Long resumeId,
                            MultipartFile multipartFile){

        PhotoDetailResponse photoDetailResponse = photoService.createPhoto(resumeId ,multipartFile);

        return photoDetailResponse;
    }

    @ResponseBody
    @PatchMapping("resume/{resumeId}/photo/{photoId}")
    public PhotoDetailResponse updatePhoto(@PathVariable(value="resumeId") Long resumeId,
                            @PathVariable(value="photoId") Long photoId,
                            MultipartFile multipartFile){

        PhotoDetailResponse photoDetailResponse = photoService.updatePhoto(photoId, resumeId, multipartFile);

        return photoDetailResponse;
    }
}
