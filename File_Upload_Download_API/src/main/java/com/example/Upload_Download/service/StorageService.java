package com.example.Upload_Download.service;

import com.example.Upload_Download.model.FileData;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StorageService {
    void uploadFile(MultipartFile file) throws IOException;
    FileData getFile(Long id);
    List<FileData> getAllFiles();

}
