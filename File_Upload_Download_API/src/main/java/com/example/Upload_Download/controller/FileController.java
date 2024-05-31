package com.example.Upload_Download.controller;

import com.example.Upload_Download.model.FileData;
import com.example.Upload_Download.service.StorageService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/filepractice")
public class FileController {

    private StorageService service;

    public FileController(StorageService service) {
        super();
        this.service = service;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            service.uploadFile(file);
            message = "Uploaded successfully: " + file.getOriginalFilename();
            return new ResponseEntity<String>(message, HttpStatus.OK);
        } catch (Exception e) {
            message = "Failed to upload file: " + file.getOriginalFilename();
            return new ResponseEntity<String>(message, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable("id") Long id) {
        FileData fileData = service.getFile(id);

        if (fileData == null || fileData.getFileData() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileData.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileData.getName() + "\"")
                .body(fileData.getFileData());
    }

    @GetMapping("/all")
    public ResponseEntity<List<ResponseEntity<byte[]>>> getAllFiles() {
        List<FileData> files = service.getAllFiles();
        List<ResponseEntity<byte[]>> responseEntities = new ArrayList<>();

        for (FileData file : files) {
            ResponseEntity<byte[]> responseEntity = ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(file.getType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                    .body(file.getFileData());

            responseEntities.add(responseEntity);
        }

        return ResponseEntity.ok(responseEntities);
    }

}
