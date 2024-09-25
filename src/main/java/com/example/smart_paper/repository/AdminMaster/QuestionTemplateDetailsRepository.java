package com.example.smart_paper.repository.AdminMaster;

import com.example.smart_paper.models.QuestionTemplateDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionTemplateDetailsRepository extends JpaRepository<QuestionTemplateDetails, Long> {
    // Custom query methods (if needed)
}
