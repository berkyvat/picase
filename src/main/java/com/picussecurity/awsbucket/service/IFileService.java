package com.picussecurity.awsbucket.service;

import java.io.IOException;
import java.util.List;

public interface IFileService {

    String writeFileToS3(String text) throws IOException;

    String getFileContent(String key);

    List<String> getFileList();
}
