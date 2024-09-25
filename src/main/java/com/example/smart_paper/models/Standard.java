package com.example.smart_paper.models;

import lombok.*;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "standard", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name") // Ensures the name field is unique
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Standard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;  // e.g., "Grade 1", "Class 10"

    @Column(nullable = true)
    private String description;

    @Column(name = "display_name", nullable = true)
    private String displayName;

    @Column(name = "creation_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "updation_date", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updationDate;

    @Column(name = "username", nullable = false)
    private String username;  // This field tracks who created or updated the entry

    // Automatically set timestamps on insert/update
    @PrePersist
    protected void onCreate() {
        this.creationDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updationDate = new Date();
    }

    @Override
    public String toString() {
        return name;  // Customize toString to return the name field
    }
}
