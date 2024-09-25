package com.example.smart_paper.repository.AdminMaster;

import com.example.smart_paper.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    // Define custom queries here if needed
}
