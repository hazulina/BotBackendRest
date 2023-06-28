package com.botbackendrest.controller;

import com.botbackendrest.service.pictureService.PictureService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/picture")
@AllArgsConstructor
public class PictureController {
    private final PictureService pictureService;

    public ResponseEntity<byte[]> getPictureById(int pictureId){
        return pictureService.getPictureById(pictureId);
    }
}
