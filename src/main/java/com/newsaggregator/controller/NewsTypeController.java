package com.newsaggregator.controller;

import com.newsaggregator.DTO.NewsTypeRequestDTO;
import com.newsaggregator.DTO.NewsTypeResponseDTO;
import com.newsaggregator.mapper.NewsTypeMapper;
import com.newsaggregator.service.NewsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NewsTypeController {

    @Autowired
    private NewsTypeService newsTypeService;

    @PostMapping("/newsType")
    public List<NewsTypeResponseDTO> createNewsType(@RequestBody List<NewsTypeRequestDTO> newsTypeRequestDTO){
        return NewsTypeMapper.responseDTO(newsTypeService.saveNewsTypes(newsTypeRequestDTO));
    }

    @GetMapping("/newsType")
    public List<NewsTypeResponseDTO> getAllNewsType(){
        return  NewsTypeMapper.responseDTO(newsTypeService.getAll());
    }


}
