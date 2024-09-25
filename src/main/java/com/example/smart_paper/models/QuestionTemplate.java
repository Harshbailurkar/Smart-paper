package com.example.smart_paper.models;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "question_template")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "total_marks", nullable = false)
    private double totalMarks;

    @Column(nullable = false)
    private boolean isActive;

    @Column(name = "creation_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "updation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updationDate;

    @Column(nullable = false)
    private String username;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    // Lifecycle hooks
    @PrePersist
    protected void onCreate() {
        this.creationDate = new Date();
        this.isActive = true; // Default value
        this.totalMarks = 0.0; // Default value
    }

    @PreUpdate
    protected void onUpdate() {
        this.updationDate = new Date();
    }
}
