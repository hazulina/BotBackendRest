package com.botbackendrest.service.pictureService;

import org.springframework.http.ResponseEntity;

public interface PictureService {
    ResponseEntity<byte[]> getPictureById(int pictureId);
}
