package com.example.Email_builder.repository;
import com.example.Email_builder.model.EmailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface EmailTemplateRepository extends JpaRepository<EmailTemplate, Long >{

}
