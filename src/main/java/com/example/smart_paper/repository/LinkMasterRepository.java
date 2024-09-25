package com.example.smart_paper.repository;

import com.example.smart_paper.models.LinkMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkMasterRepository extends JpaRepository<LinkMaster, Long> {

    @Query("SELECT l FROM LinkMaster l WHERE l.usertype.id = :userTypeId AND l.isActive = true")
    List<LinkMaster> findAllByUserTypeId(@Param("userTypeId") Long userTypeId);

}
