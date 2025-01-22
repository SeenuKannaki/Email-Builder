package com.example.Email_builder.controller;

import com.example.Email_builder.model.EmailTemplate;
import com.example.Email_builder.service.EmailTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class EmailController {

    @Autowired
    private EmailTemplateService service;

    // Get all templates
    @GetMapping("/templates")
    public ResponseEntity<List<EmailTemplate>> getEmailLayout() {
        return ResponseEntity.ok(service.getAllTemplates());
    }

    // Save email template
    @PostMapping("/templates")
    public ResponseEntity<EmailTemplate> saveTemplate(@RequestBody EmailTemplate template) {
        return ResponseEntity.ok(service.saveTemplate(template));
    }

    // Upload email configuration (save template)
    @PostMapping("/uploadEmailConfig")
    public ResponseEntity<EmailTemplate> uploadEmailConfig(@RequestBody EmailTemplate config) {
        EmailTemplate savedTemplate = service.saveTemplate(config);
        return ResponseEntity.ok(savedTemplate);
    }

    // Delete a template
    @DeleteMapping("/templates/{id}")
    public ResponseEntity<String> deleteTemplate(@PathVariable Long id) {
        service.deleteTemplate(id);
        return ResponseEntity.ok("Template deleted successfully");
    }

    // Upload image
    @PostMapping("/uploadImage")
    public ResponseEntity<String> uploadImage(@RequestParam("files") MultipartFile file) {
        String fileUrl = service.uploadImage(file);
        return ResponseEntity.ok("File uploaded successfully: " + fileUrl);
    }

    // Download email template (HTML file)
    @GetMapping("/templates/{id}")
    public ResponseEntity<Resource> downloadTemplate(@PathVariable Long id) {
        EmailTemplate template = service.getTemplateById(id);
        String templateContent = template.getContent();
        String filename = "email-template-" + id + ".html";
        ByteArrayResource resource = new ByteArrayResource(templateContent.getBytes());

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
            .contentType(MediaType.TEXT_HTML)
            .body(resource);
    }
}
