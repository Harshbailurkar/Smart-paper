package com.example.smart_paper.repository.AdminMaster;

import com.example.smart_paper.models.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    // You can define additional query methods if needed
}
