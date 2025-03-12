package com.example.demo.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Configuration
@Component
public class CommonConfig {
    @Value("${file.upload-dir}")
    private String uploadDir;

    @Value("${file.static-path}")
    private String staticPath;

    public String getStaticPath() {
        return staticPath;
    }

    public String getUploadDir() {
        return uploadDir;
    }
}


