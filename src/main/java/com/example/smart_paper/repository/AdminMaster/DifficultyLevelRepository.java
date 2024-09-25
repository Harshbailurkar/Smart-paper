package com.example.smart_paper.repository.AdminMaster;

import com.example.smart_paper.models.DifficultyLevel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DifficultyLevelRepository extends JpaRepository<DifficultyLevel, Long> {
    // Custom query methods (if needed)
}
