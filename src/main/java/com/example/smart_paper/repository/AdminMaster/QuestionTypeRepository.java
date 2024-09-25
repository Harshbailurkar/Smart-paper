package com.example.smart_paper.repository.AdminMaster;

import com.example.smart_paper.models.QuestionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionTypeRepository extends JpaRepository<QuestionType, Long> {
    // Define any custom queries here if necessary
}
