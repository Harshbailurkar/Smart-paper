package com.example.smart_paper.repository.AdminMaster;

import com.example.smart_paper.models.SubjectOffering;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectOfferingRepository extends JpaRepository<SubjectOffering, Long> {
    // Custom query methods (if needed)
}
