package com.example.smart_paper.repository;

import com.example.smart_paper.models.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTypeRepo extends JpaRepository<UserType, Long> {
    UserType findByName(String name);
}
