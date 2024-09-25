package com.example.smart_paper.models;

import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "board", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name"),
        @UniqueConstraint(columnNames = "code")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name; // CBSE, ICSE

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = true)
    private String description;

    @Column(nullable = true)
    private String country;

    @Column(name = "establishment_year", nullable = true)
    @Min(1800)
    private Integer establishmentYear;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(nullable = true)
    private String website;

    @Email
    @Column(name = "contact_email", nullable = true)
    private String contactEmail;

    @Column(nullable = true)
    private String region;

    @Column(name = "grade_levels_covered", nullable = true)
    private String gradeLevelsCovered;

    @Column(name = "affiliated_schools_count", nullable = true)
    @Min(0)
    private Integer affiliatedSchoolsCount;

    @Column(name = "examination_pattern", nullable = true)
    private String examinationPattern;

    @Column(name = "syllabus_url", nullable = true)
    private String syllabusUrl;

    @Column(name = "creation_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "updation_date", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updationDate;

    @Column(name = "username", nullable = false)
    private String username;

    // Automatically set timestamps before inserting/updating records
    @PrePersist
    protected void onCreate() {
        this.creationDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updationDate = new Date();
    }
}
