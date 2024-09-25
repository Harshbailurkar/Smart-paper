package com.example.smart_paper.models;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "topic")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "sort_order", columnDefinition = "int default 0")
    private int sortOrder = 0;

    @Column(name = "creation_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "updation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updationDate;

    @Column(nullable = false)
    private String username;

    // Many-to-one relationship with Chapter
    @ManyToOne
    @JoinColumn(name = "chapter_id", nullable = false)
    private Chapter chapter;

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
