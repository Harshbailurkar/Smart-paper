package com.example.smart_paper.repository.AdminMaster;

import com.example.smart_paper.models.QuestionTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionTemplateRepository extends JpaRepository<QuestionTemplate, Long> {
    // Custom query methods (if needed)
}
