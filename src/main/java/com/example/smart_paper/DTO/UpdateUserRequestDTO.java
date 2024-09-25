package com.example.smart_paper.DTO;

import com.example.smart_paper.models.UserModel;

public class UpdateUserRequestDTO {
    private String oldPassword;
    private UserModel user;

    // Getters and setters
    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
