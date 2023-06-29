package com.botbackendrest.service.pictureService;

import java.io.ByteArrayOutputStream;

public interface PictureService {
    ByteArrayOutputStream getPictureById(String folderName, String pictureId);
}
