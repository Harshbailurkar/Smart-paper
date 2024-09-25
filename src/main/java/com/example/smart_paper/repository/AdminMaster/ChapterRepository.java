package com.example.smart_paper.repository.AdminMaster;

import com.example.smart_paper.models.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    // You can define custom queries here if needed
}
