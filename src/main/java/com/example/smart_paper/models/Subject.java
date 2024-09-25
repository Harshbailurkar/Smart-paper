package com.example.smart_paper.models;

import lombok.*;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "subject")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;  // e.g., Mathematics, Science

    @Column(name = "is_active", nullable = false, columnDefinition = "boolean default true")
    private boolean isActive = true;

    @Column(name = "display_name", nullable = false)
    private String displayName;

    @Column(name = "creation_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "updation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updationDate;

    @Column(name = "username", nullable = false)
    private String username;  // This field tracks who created or updated the entry

    // Automatically set timestamps on insert/update
    @PrePersist
    protected void onCreate() {
        this.creationDate = new Date();
        this.isActive = true;  // Default value for isActive when inserted
    }

    @PreUpdate
    protected void onUpdate() {
        this.updationDate = new Date();
    }
}
