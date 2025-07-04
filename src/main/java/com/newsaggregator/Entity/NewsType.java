package com.newsaggregator.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class NewsType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String newsType;

    @ManyToMany(mappedBy = "preferences")
    private List<Users> user;
    @OneToMany(mappedBy = "newsType")
    private List<News> news;
}
