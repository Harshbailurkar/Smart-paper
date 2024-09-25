package com.example.smart_paper.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "question_paper")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionPaper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title; // Title of the question paper

    @Column(nullable = false)
    private String description; // Description of the question paper

    @ManyToMany
    @JoinTable(
            name = "question_paper_questions",
            joinColumns = @JoinColumn(name = "question_paper_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private List<Question> questions; // Questions in the paper

    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "updation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updationDate;

    @Column(nullable = false)
    private String username; // Tracks who created or updated the entry

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
