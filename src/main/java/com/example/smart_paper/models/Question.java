package com.example.smart_paper.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "question")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question_statement", nullable = false)
    private String questionStatement;

    @Column(name = "answer")
    private String answer;  // Comma-separated description, remark, single-word answer

    @Column(name = "is_approved", nullable = false, columnDefinition = "boolean default true")
    private boolean isApproved = true;

    @Column(name = "is_deleted", nullable = false, columnDefinition = "boolean default false")
    private boolean isDeleted = false;

    @Column(name = "marks", columnDefinition = "double default 0.0")
    private double marks = 0.0;

    @Column(name = "question_file_path")
    private String questionFilePath;

    @Column(name = "question_filename")
    private String questionFilename;

    @Column(name = "answer_filename")
    private String answerFileName;

    @Column(name = "answer_file_path")
    private String answerFilePath;

    @Column(name = "creation_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "updation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updationDate;

    @Column(nullable = false)
    private String username;

    // Relationships
    @ManyToOne
    @JoinColumn(name = "question_type_id")
    private QuestionType questionType;

    @ManyToOne
    @JoinColumn(name = "difficulty_level_id")
    private DifficultyLevel difficultyLevel;

    @ManyToOne
    @JoinColumn(name = "subject_offering_id")
    private SubjectOffering subjectOffering;

    @ManyToOne
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    // Lifecycle callbacks
    @PrePersist
    protected void onCreate() {
        this.creationDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updationDate = new Date();
    }
}
