package com.botbackendrest.controller;

import com.botbackendrest.service.pictureService.PictureService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("/api/picture")
@AllArgsConstructor
public class PictureController {
    private final PictureService pictureService;

    @GetMapping("/{folderName}/{pictureId}")
    public ResponseEntity<byte[]> getPictureById(@PathVariable String folderName, @PathVariable String pictureId) {
        ByteArrayOutputStream downloadInputStream = pictureService.getPictureById(folderName, pictureId);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + pictureId + "\"")
                .body(downloadInputStream.toByteArray());
    }
}
