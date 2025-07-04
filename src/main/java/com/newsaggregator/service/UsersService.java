package com.newsaggregator.service;

import com.newsaggregator.DTO.PreferencesDTO;
import com.newsaggregator.DTO.PreferencesResponseDTO;
import com.newsaggregator.Entity.NewsType;
import com.newsaggregator.Entity.Users;
import com.newsaggregator.exception.UserNotFoundException;
import com.newsaggregator.repository.NewsTypeRepository;
import com.newsaggregator.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private NewsTypeRepository newsTypeRepository;

    public void setPreferencesByIds(List<Integer> preferenceIds, Users user) {
        List<NewsType> preferences = newsTypeRepository.findAllById(preferenceIds);
        user.setPreferences(preferences);
        usersRepository.save(user);
    }

    public List<PreferencesResponseDTO> getPreferencesForUser(Users user) {
        List<NewsType> preferences = user.getPreferences();
        return preferences.stream().map(newsType -> {
            PreferencesResponseDTO dto = new PreferencesResponseDTO();
            dto.setId(newsType.getId());
            dto.setPreferenceName(newsType.getNewsType());
            return dto;
        }).collect(Collectors.toList());
    }

    public Users findUserByName(String username) {
        return usersRepository.findByName(username);
    }
}
