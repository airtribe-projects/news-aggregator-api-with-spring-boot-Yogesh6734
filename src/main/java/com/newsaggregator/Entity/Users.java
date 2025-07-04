package com.newsaggregator.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String name;
    private String password;
    private boolean isEnabled;
    private String role;

    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
    private VerificationToken verificationToken;

    @ManyToMany
    @JoinTable(name = "user_preferences",
            joinColumns = @JoinColumn(name= "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn (name = "preference_id", referencedColumnName = "id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "preference_id"}))
    private List<NewsType> preferences;


}
