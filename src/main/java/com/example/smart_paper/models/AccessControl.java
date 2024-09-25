package com.example.smart_paper.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "access_control")
public class AccessControl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_blocked", nullable = false, columnDefinition = "boolean default false")
    private boolean isBlocked = false;

    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "updation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updationDate;


    @Column(name = "username", nullable = false)
    private String username;

    // ManyToOne relationships with User and LinkMaster
    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "link_master_id", nullable = false)
    private LinkMaster linkMaster;

    // Constructors
    public AccessControl() {
    }

    public AccessControl(UserModel user, LinkMaster linkMaster, String username) {
        this.user = user;
        this.linkMaster = linkMaster;
        this.username = username;
        this.creationDate = new Date(); // Set creation date at instantiation
    }

    // Getters and Setters
    // Omitted for brevity

    // Automatically set creation and update timestamps
    @PrePersist
    protected void onCreate() {
        this.creationDate = new Date();
        this.isBlocked = false;  // Default value when created
    }

    @PreUpdate
    protected void onUpdate() {
        this.updationDate = new Date();
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

}
