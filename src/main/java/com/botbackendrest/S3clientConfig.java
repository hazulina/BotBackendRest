package com.botbackendrest;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3clientConfig {

    @Value("${s3.credentials.key.id}")
    private String awsKeyId;

    @Value("${s3.credentials.key}")
    private String awsKey;
    @Value("${s3.service.endpoint}")
    private String awsEndpointUrl;
    @Value("${s3.signing.region}")
    private String signingRegion;

    @Bean
    public AmazonS3 s3client() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsKeyId, awsKey);
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withEndpointConfiguration(
                        new AmazonS3ClientBuilder.EndpointConfiguration(awsEndpointUrl, signingRegion))
                .build();
    }
}
