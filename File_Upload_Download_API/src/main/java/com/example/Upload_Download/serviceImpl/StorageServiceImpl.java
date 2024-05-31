package com.example.Upload_Download.serviceImpl;

import com.example.Upload_Download.model.FileData;
import com.example.Upload_Download.repository.StorageRepository;
import com.example.Upload_Download.service.StorageService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class StorageServiceImpl implements StorageService {
    private StorageRepository repo;

    public StorageServiceImpl(StorageRepository repo) {
        super();
        this.repo = repo;
    }

    @Override
    public void uploadFile (MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileData savedFile = new FileData(fileName, file.getContentType(), file.getBytes());
        repo.save(savedFile);
    }

    @Override
    public FileData getFile(Long id) {
        Optional<FileData> optionalFileData = repo.findById(id);
        return optionalFileData.orElse(null);
    }

    @Override
    public List<FileData> getAllFiles() {
        return repo.findAll();
    }
}
