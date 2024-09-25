package com.example.smart_paper.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "question_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type;  // MCQ, Fill in the blanks, Match the pair, Subjective

    @Column(name = "display_name", nullable = false)
    private String displayName;

    @Column(name = "is_active", columnDefinition = "boolean default true")
    private boolean isActive = true;

    @Column(name = "creation_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "updation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updationDate;

    @Column(nullable = false)
    private String username;  // Tracks who created or updated the entry

    // Lifecycle hooks
    @PrePersist
    protected void onCreate() {
        this.creationDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updationDate = new Date();
    }
}
