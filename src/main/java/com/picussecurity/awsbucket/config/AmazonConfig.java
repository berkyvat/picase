package com.picussecurity.awsbucket.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfig {
    private final String accessKey;
    private final String secretKey;
    private final String region;

    public AmazonConfig(@Value("${picus.amazon.s3.accesskey}")String accessKey,
                        @Value("${picus.amazon.s3.secretkey}")String secretKey,
                        @Value("${picus.amazon.s3.region}") String region){
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.region = region;
    }


    @Bean
    public AmazonS3 s3() {
        AWSCredentials awsCredentials =
                new BasicAWSCredentials(accessKey, secretKey);
        return AmazonS3ClientBuilder
                .standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();

    }
}
