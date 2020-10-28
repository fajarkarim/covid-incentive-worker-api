package com.morningstar.covidworkerincentiveapi.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {
    private final Path fileStorageLocation;

    public StorageService() {
        this.fileStorageLocation = Paths.get("src/main/upload/")
            .toAbsolutePath().normalize();
    }

    public boolean storeFile(MultipartFile file) {
        try {
            final String fileName = file.getOriginalFilename();
            Files.createDirectories(this.fileStorageLocation);
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
            return false;
        }
    }
}
