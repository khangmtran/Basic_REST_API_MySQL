package com.example.Upload_Download.repository;

import com.example.Upload_Download.model.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageRepository extends JpaRepository<FileData, Long> {

}
