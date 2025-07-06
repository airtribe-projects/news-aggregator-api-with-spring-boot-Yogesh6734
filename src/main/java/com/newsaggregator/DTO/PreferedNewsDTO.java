package com.newsaggregator.DTO;

import lombok.Data;

import java.util.List;

@Data
public class PreferedNewsDTO {
    public List<NewsDto> sources;
}
