package com.example.Upload_Download.model;

import jakarta.persistence.*;


@Entity
@Table(name = "image_data")
public class FileData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    @Lob
    @Column(name = "file_data", length = 10485760)
    private byte[] fileData;

    public FileData(){

    }

    public FileData(String fileName, String contentType, byte[] data) {
        this.name = fileName;
        this.type = contentType;
        this.fileData = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] imageData) {
        this.fileData = imageData;
    }
}
