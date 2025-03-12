package com.example.demo.service;

import com.example.demo.config.CommonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileUploader {
    @Autowired
    private CommonConfig config;

    public String saveFile(MultipartFile file, String fileName) throws IOException {
        String upDir = config.getUploadDir();
        String filePath = config.getStaticPath() + File.separator + upDir;
        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String originalFileName = file.getOriginalFilename();
        String fileExt = originalFileName.substring(originalFileName.lastIndexOf("."));
        String imgPath = filePath + File.separator + fileName + fileExt;

        File dest = new File(imgPath);
        file.transferTo(dest);
        System.out.println(dest.getAbsolutePath());
        return upDir + File.separator + fileName + fileExt;
    }
}
