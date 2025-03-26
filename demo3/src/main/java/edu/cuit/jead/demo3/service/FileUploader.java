package edu.cuit.jead.demo3.service;

import edu.cuit.jead.demo3.config.CommConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileUploader {
    @Autowired
    private CommConfig config;

    public String saveFile(MultipartFile file, String fileName)
            throws IOException {
        String upDir = config.getUploadDir();
        String dirPath = config.getStaticPath() + File.separator + upDir;
        File dir = new File(dirPath);
        if(!dir.exists()) {
            dir.mkdir();
        }

        String oriName = file.getOriginalFilename();
        String fileExt= oriName.substring(oriName.lastIndexOf("."));
        String imgPath = dirPath + File.separator + fileName + fileExt;

        File dest = new File(imgPath);
        file.transferTo(dest);
        System.out.println(dest.getAbsoluteFile());
        return upDir + "/" + fileName + fileExt;
    }
}
