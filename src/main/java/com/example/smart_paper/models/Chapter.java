package com.example.smart_paper.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Date;

@Entity
@Table(name = "chapter")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "sort_order", nullable = false, columnDefinition = "int default 0")
    private int sortOrder;

    @Column(name = "creation_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "updation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updationDate;

    @Column(nullable = false)
    private String username;  // Tracks who created or updated the entry

    @PrePersist
    protected void onCreate() {
        this.creationDate = new Date();
        if (this.sortOrder == 0) {
            this.sortOrder = 0;  // Set default sort order if not provided
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updationDate = new Date();
    }
}
