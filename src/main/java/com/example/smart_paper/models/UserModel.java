package com.example.smart_paper.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String mobileNo;
    private String JWTToken;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_type_id")
    private UserType userType;

    // Setter for oldPassword (optional)
    // Getter for oldPassword
    @Setter
    @Getter
    @Transient // This field is not persisted in the database
    private String oldPassword; // For storing the old password temporarily

}

