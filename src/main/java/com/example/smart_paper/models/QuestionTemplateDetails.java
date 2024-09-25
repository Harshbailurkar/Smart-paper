package com.example.smart_paper.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "question_template_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionTemplateDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "no_of_question", nullable = false)
    private int noOfQuestion;

    @Column(name = "per_question_marks", nullable = false)
    private double perQuestionMarks;

    @Column(nullable = false)
    private boolean isActive;

    @Column(name = "sort_order", nullable = false)
    private int sortOrder;

    @Column(name = "creation_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "updation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updationDate;

    @Column(nullable = false)
    private String username;

    @ManyToOne
    @JoinColumn(name = "question_template_id", nullable = false)
    private QuestionTemplate questionTemplate;

    @ManyToOne
    @JoinColumn(name = "question_type_id", nullable = false)
    private QuestionType questionType;

    // Lifecycle hooks
    @PrePersist
    protected void onCreate() {
        this.creationDate = new Date();
        this.isActive = true; // Default value
        this.perQuestionMarks = 0.0; // Default value
        this.noOfQuestion = 0; // Default value
        this.sortOrder = 0; // Default value
    }

    @PreUpdate
    protected void onUpdate() {
        this.updationDate = new Date();
    }
}
