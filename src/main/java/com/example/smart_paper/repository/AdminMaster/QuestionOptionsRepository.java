package com.example.smart_paper.repository.AdminMaster;


import com.example.smart_paper.models.QuestionOptions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionOptionsRepository extends JpaRepository<QuestionOptions, Long> {
    // Custom query methods (if needed)
}
