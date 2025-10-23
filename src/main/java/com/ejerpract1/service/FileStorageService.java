/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejerpract1.service;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Juan
 */

@Service
public class FileStorageService {

    private final Path uploadsDir = Paths.get("./uploads");

    public FileStorageService() throws IOException {
        if (!Files.exists(uploadsDir)) {
            Files.createDirectories(uploadsDir);
        }
    }

    public String store(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return null;
        }
        String original = file.getOriginalFilename();
        String ext = "";
        if (original != null && original.contains(".")) {
            ext = original.substring(original.lastIndexOf('.'));
        }
        String filename = "book_" + UUID.randomUUID().toString() + ext;
        Path target = uploadsDir.resolve(filename).toAbsolutePath();
        Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
        return "/uploads/" + filename;
    }

    public boolean delete(String ruta) {
        if (ruta == null || ruta.isBlank()) return false;
        try {
            String name = Paths.get(ruta).getFileName().toString();
            Path target = uploadsDir.resolve(name);
            return Files.deleteIfExists(target);
        } catch (Exception e) {
            return false;
        }
    }
}
