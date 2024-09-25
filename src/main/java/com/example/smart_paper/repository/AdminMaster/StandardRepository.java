package com.example.smart_paper.repository.AdminMaster;

import com.example.smart_paper.models.Standard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StandardRepository extends JpaRepository<Standard, Long> {
    // You can define custom query methods here if needed
}
