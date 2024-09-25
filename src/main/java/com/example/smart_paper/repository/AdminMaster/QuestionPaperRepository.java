package com.example.smart_paper.repository.AdminMaster;

import com.example.smart_paper.models.QuestionPaper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionPaperRepository extends JpaRepository<QuestionPaper, Long> {
    // Custom query methods (if needed)
}
