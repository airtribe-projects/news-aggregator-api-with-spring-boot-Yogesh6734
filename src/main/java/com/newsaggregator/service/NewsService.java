package com.newsaggregator.service;

import com.newsaggregator.DTO.PreferedNewsDTO;
import com.newsaggregator.DTO.PreferencesResponseDTO;
import com.newsaggregator.Entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class NewsService {

    @Autowired
    private UsersService usersService;

    @Autowired
    private WebClient webClient;

    @Value("${NEWS_API_KEY}")
    private String apiKey;

    public Mono<PreferedNewsDTO> getPreferredNews(Users loggedInUser) {
         List<PreferencesResponseDTO> userPreferences= usersService.
                getPreferencesForUser(loggedInUser);

        List<Mono<PreferedNewsDTO>> preferedNewsDTOMono= userPreferences.stream().map(
                preference -> webClient.get().uri(
                uriBuilder -> uriBuilder
                        .scheme("https")
                        .host("newsapi.org")
                        .path("/v2/top-headlines/sources")
                        .queryParam("apiKey",apiKey)
                        .queryParam("category",preference.getPreferenceName())
                        .queryParam("language","en").build())
                .retrieve().bodyToMono(PreferedNewsDTO.class)).toList();

        return Flux.merge(preferedNewsDTOMono).flatMap(preferredNews ->
                Flux.fromIterable(preferredNews.getSources())).collectList().
                map(preferredNewsList -> {
            PreferedNewsDTO combinePreferredNews=new PreferedNewsDTO();
            combinePreferredNews.setSources(preferredNewsList);
                return combinePreferredNews;
        });
    }
}
