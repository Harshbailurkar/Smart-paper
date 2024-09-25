package com.example.smart_paper.repository.AdminMaster;

import com.example.smart_paper.models.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    // You can define custom query methods here if needed
}
