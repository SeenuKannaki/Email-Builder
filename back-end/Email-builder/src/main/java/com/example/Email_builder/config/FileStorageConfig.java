package com.example.Email_builder.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "file")
public class FileStorageConfig {
    
    private String uploadDir;

    // Getter for uploadDir
    public String getUploadDir() {
        return uploadDir;
    }

    // Setter for uploadDir
    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}
