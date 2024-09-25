package com.example.smart_paper.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "link_master")
public class LinkMaster {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Getter
    @NotBlank
    private String link;

    private boolean isActive = true; // Default value

    @Getter
    @NotBlank
    private String displayName;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updationDate;


    @Getter
    @ManyToOne
    @JsonBackReference
    @JsonIgnore
    @JoinColumn(name = "user_type_id") // Assuming the foreign key column in the database
    private UserType usertype;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

}
