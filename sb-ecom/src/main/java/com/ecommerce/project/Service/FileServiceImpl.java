package com.ecommerce.project.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements  FileService {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        // File names of the current/ original File
        String originalFilename = file.getOriginalFilename();

        // Generate a Unique File Name
        String randomId = UUID.randomUUID().toString();
        String fileName = randomId.concat(originalFilename.substring(originalFilename.lastIndexOf('.')));
        String filePath = path + File.separator + fileName;

        // Check if Path exists or not. if NOT Create
        File folder = new File(path);
        if(!folder.exists()){
            folder.mkdir();
        }

        // Upload to the server
        Files.copy(file.getInputStream(), Paths.get(filePath));

        // Returning File name
        return fileName;
    }
}
