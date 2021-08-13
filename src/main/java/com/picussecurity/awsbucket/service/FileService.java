package com.picussecurity.awsbucket.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.util.IOUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileService implements IFileService {

    private final AmazonS3 amazonS3;
    private final String bucketName;

    public FileService(AmazonS3 amazonS3,
                       @Value("${picus.amazon.s3.bucketname}") String bucketName) {
        this.amazonS3 = amazonS3;
        this.bucketName = bucketName;
    }


    @Override
    public String writeFileToS3(String text) {
        try {
            UUID uuid = UUID.randomUUID();
            String uuidAsString = uuid.toString();
            Path tempFile = Files.createTempFile(null, null);

            // Writes a string to the above temporary file
            Files.write(tempFile, text.getBytes(StandardCharsets.UTF_8));

            amazonS3.putObject(
                    bucketName,
                    uuidAsString,
                    tempFile.toFile()
            );

            return uuidAsString;
        } catch (Exception ex) {
            throw new RuntimeException("Error while writing text to s3", ex);
        }
    }

    @Override
    public String getFileContent(String key) {
        byte[] content = null;
        try {
            S3Object s3object = amazonS3.getObject(bucketName, key);
            S3ObjectInputStream inputStream = s3object.getObjectContent();
            content = IOUtils.toByteArray(inputStream);

            return  new String(content);
        } catch (Exception ex) {
            throw new RuntimeException("Error while reading text from s3", ex);
        }

    }

    @Override
    public List<String> getFileList(){
        try {
            List<String> fileNameList = new ArrayList<>();
            ObjectListing objectListing = amazonS3.listObjects(bucketName);
            for(S3ObjectSummary os : objectListing.getObjectSummaries()) {
                fileNameList.add(os.getKey());
            }

            return fileNameList;
        } catch (Exception ex) {
            throw new RuntimeException("Error while reading text from s3", ex);
        }
    }

}
