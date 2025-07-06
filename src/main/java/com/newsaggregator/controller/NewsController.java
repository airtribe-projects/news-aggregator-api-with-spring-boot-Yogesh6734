package com.newsaggregator.controller;

import com.newsaggregator.DTO.PreferedNewsDTO;
import com.newsaggregator.Entity.Users;
import com.newsaggregator.service.NewsService;
import com.newsaggregator.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

@RestController
public class NewsController {

    @Autowired
    private UsersService usersService;
    @Autowired
    private NewsService newsService;

    @GetMapping("/news")
    public Mono<PreferedNewsDTO> getNews(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Users loggedInUser = usersService.findUserByName(username);
       return newsService.getPreferredNews(loggedInUser);

    }
}
