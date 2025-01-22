package com.example.Email_builder.service;

import com.example.Email_builder.model.EmailTemplate;
import com.example.Email_builder.repository.EmailTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class EmailTemplateService {

    @Autowired
    private EmailTemplateRepository repository;

    @Value("${file.upload-dir}")
    private String uploadDir; // Path to save files

    public EmailTemplateService(EmailTemplateRepository repository) {
        this.repository = repository;
    }

    // Fetch all templates
    public List<EmailTemplate> getAllTemplates() {
        return repository.findAll();
    }

    // Save a template
    public EmailTemplate saveTemplate(EmailTemplate template) {
        return repository.save(template);
    }

    // Delete a template
    public void deleteTemplate(Long id) {
        repository.deleteById(id);
    }

    // Get a template by ID
    public EmailTemplate getTemplateById(Long id) {
        Optional<EmailTemplate> template = repository.findById(id);
        return template.orElseThrow(() -> new RuntimeException("Template not found"));
    }

    // Upload image to the server
    public String uploadImage(MultipartFile file) {
        try {
            String filename = file.getOriginalFilename();
            if (filename == null) {
                throw new IOException("Failed to get file name");
            }

            Path path = Paths.get(uploadDir, filename);
            Files.createDirectories(path.getParent());  // Ensure directories exist
            Files.copy(file.getInputStream(), path);    // Save the file

            return path.toString(); // Return the file path
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to upload file", e);
        }
    }
}
