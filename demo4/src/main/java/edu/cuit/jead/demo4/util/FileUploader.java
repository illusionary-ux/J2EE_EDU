package edu.cuit.jead.demo4.util;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Component
public class FileUploader {

    @Value("${file.upload.path}")
    private String uploadPath;

    //// 允许的文件类型（图片格式）
    private static final String[] ALLOWED_EXTENSIONS = {"jpg", "jpeg", "png", "gif"};

    public String uploadAvatar(MultipartFile file,Long uid) throws IOException,IllegalStateException {
        // 校验文件名不为空
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        // 校验文件类型
        String originalFilename = file.getOriginalFilename();
        String fileExtension = getFileExtension(originalFilename);
        if (!isValidExtension(fileExtension)) {
            throw new IllegalArgumentException("仅支持以下图片格式: " + String.join(", ", ALLOWED_EXTENSIONS));
        }

        String formattedUid = String.format("UID%04d", uid); // 格式化UID（如UID0001）
        String timestamp = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String randomCode = UUID.randomUUID().toString().substring(0, 4); // 4位随机码防冲突
        String newFilename = String.format("%s_%s_%s.%s", formattedUid, timestamp, randomCode, fileExtension);

        // 3. 创建目标目录（如果不存在）
        Path targetDir = Paths.get(uploadPath);
        if (!Files.exists(targetDir)) {
            Files.createDirectories(targetDir);
        }

        // 4. 保存文件
        Path targetPath = targetDir.resolve(newFilename);
        file.transferTo(targetPath.toFile());

        // 5. 返回相对路径（如：/static/avatar/UID0001_20240402_abcd.jpg）
        return uploadPath + newFilename;
    }

    private String getFileExtension(String filename) {
        if (filename == null||filename.lastIndexOf(".") == -1) {
            return "";
        }

        return filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
    }


    //校验文件扩展名是否合法
    private boolean isValidExtension(String extension) {
        for (String allowed : ALLOWED_EXTENSIONS) {
            if (allowed.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }


}
