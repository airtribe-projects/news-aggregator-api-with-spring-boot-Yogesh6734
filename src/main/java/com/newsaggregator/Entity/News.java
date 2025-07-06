package com.newsaggregator.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;
    private String title;
    private  String content;
    private String imageUrl;
    private Date publishedAt;

    @ManyToOne
    @JoinColumn(name = "news_type_id")
    private NewsType newsType;
}
