package com.newsaggregator.service;

import com.newsaggregator.DTO.NewsTypeRequestDTO;
import com.newsaggregator.Entity.NewsType;
import com.newsaggregator.repository.NewsTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsTypeService {

    @Autowired
    private NewsTypeRepository newsTypeRepository;
    public List<NewsType> saveNewsTypes(List<NewsTypeRequestDTO> newsTypeRequestDTO) {
        List<NewsType> newsTypeList = newsTypeRequestDTO.stream().map(dto -> {
            NewsType newsType = new NewsType();
            newsType.setNewsType(dto.getNewsType());
            return newsType;
        }).toList();
        return newsTypeRepository.saveAll(newsTypeList);
    }

    public List<NewsType> getAll() {
        return newsTypeRepository.findAll();
    }
}
