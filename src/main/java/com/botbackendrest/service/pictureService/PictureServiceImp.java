package com.botbackendrest.service.pictureService;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
@Slf4j
public class PictureServiceImp implements PictureService {

    private final AmazonS3 amazonS3;
    @Value("${s3.query.url}")
    private String awsQueryUrl;

    @Override
    public ByteArrayOutputStream getPictureById(String folderName, String pictureId) {
        String url = folderName + "/" + pictureId + ".jpg";
        try (S3Object s3object = amazonS3.getObject(new GetObjectRequest(awsQueryUrl, url));
             InputStream objectContent = s3object.getObjectContent()) {

            return readImageData(objectContent);
        } catch (IOException ioException) {
            throw new RuntimeException(ioException.getMessage());
        }
    }

    private ByteArrayOutputStream readImageData(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, length);
        }
        return outputStream;
    }

    public PictureServiceImp(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }
}