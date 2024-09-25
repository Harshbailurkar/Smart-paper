package com.example.smart_paper.repository;

import com.example.smart_paper.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserModel, Integer> {
    UserModel findByEmail(String email);
}
