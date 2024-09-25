package com.example.smart_paper.repository.AdminMaster;

import com.example.smart_paper.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    // Define custom queries here if needed
}
