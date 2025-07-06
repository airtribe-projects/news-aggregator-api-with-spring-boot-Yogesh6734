package com.newsaggregator.DTO;


import lombok.Data;

@Data
public class NewsDto {

    private String description;
    private  String name;
    private String category;
    private String url;

}

