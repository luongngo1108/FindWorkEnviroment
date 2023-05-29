package com.oose.jobportal.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface FileUploadService {
    Map<?, ?> uploadFile(MultipartFile multipartFile);

    Map<?, ?> destroyFile(Map map) throws IOException;
}
