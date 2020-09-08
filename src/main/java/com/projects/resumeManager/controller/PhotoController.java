package com.projects.resumeManager.controller;

import com.projects.resumeManager.dto.response.PhotoDetailResponse;
import com.projects.resumeManager.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PhotoController {

    @Autowired
    PhotoService photoService;

    @GetMapping("resume/{resumeId}/photo")
    public void getPhoto(@PathVariable(value="resumeId") Long resumeId,
                         Model model){

        PhotoDetailResponse photoDetailResponse = photoService.getPhoto(resumeId);

        model.addAttribute("photoDetailResponse", photoDetailResponse);
    }

    @PostMapping("resume/{resumeId}/photo")
    public void createPhoto(@PathVariable(value="resumeId") Long resumeId,
                            MultipartFile multipartFile,
                            Model model){
        PhotoDetailResponse photoDetailResponse = photoService.createPhoto(resumeId ,multipartFile);

        model.addAttribute("photoDetailResponse", photoDetailResponse);
    }

    @PatchMapping("resume/{resumeId}/photo/{photoId}")
    public void updatePhoto(@PathVariable(value="resumeId") Long resumeId,
                            @PathVariable(value="photoId") Long photoId,
                            MultipartFile multipartFile,
                            Model model){

        PhotoDetailResponse photoDetailResponse = photoService.updatePhoto(photoId, resumeId, multipartFile);

        model.addAttribute("photoDetailResponse", photoDetailResponse);
    }
}
