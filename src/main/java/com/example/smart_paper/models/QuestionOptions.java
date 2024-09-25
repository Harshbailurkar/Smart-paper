package com.example.smart_paper.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "question_options")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionOptions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private boolean isActive;

    @Column(name = "ques_options")
    private String quesoptions; // for MCQ

    @Column(name = "question_statement")
    private String questionStatement; // Group question and Match the pair

    @Column(name = "is_correct")
    private boolean isCorrect; // For MCQ

    @Column(nullable = true)
    private String answer; // Group question and Match the pair answer

    private double marks;

    @Column(nullable = false)
    private String username;

    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "updation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updationDate;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = true)
    private Question question;

    // Lifecycle hooks
    @PrePersist
    protected void onCreate() {
        this.creationDate = new Date();
        this.isActive = true; // Default value
        this.isCorrect = false; // Default value
        this.marks = 0.0; // Default value
    }

    @PreUpdate
    protected void onUpdate() {
        this.updationDate = new Date();
    }
}
