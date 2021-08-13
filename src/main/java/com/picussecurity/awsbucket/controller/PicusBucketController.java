package com.picussecurity.awsbucket.controller;

import com.picussecurity.awsbucket.service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/picus")
public class PicusBucketController {

    private final FileService fileService;

    public PicusBucketController(FileService fileService) {
        this.fileService = fileService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity listBucketFiles() {
        try {
            List<String> fileList = fileService.getFileList();

            return new ResponseEntity<>(fileList, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);

        }
    }

    @RequestMapping(value = "/put", method = RequestMethod.POST)
    public ResponseEntity putBucketFiles(
            @RequestBody String data
    ) {
        try {
            String key = fileService.writeFileToS3(data);

            return new ResponseEntity<>(key, HttpStatus.OK);
        } catch (Exception ex) {

            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);

        }
    }


    @RequestMapping(value = "/get/{key}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getBucketFiles(
            @PathVariable(value = "key") String key
    ) {
        try {
            String content = fileService.getFileContent(key);

            return new ResponseEntity<>(content, HttpStatus.OK);
        } catch (Exception ex) {


            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }
    }


}
