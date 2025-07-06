package com.newsaggregator.controller;

import com.newsaggregator.DTO.PreferencesDTO;
import com.newsaggregator.DTO.PreferencesResponseDTO;
import com.newsaggregator.Entity.Users;
import com.newsaggregator.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/preferences")
public class PreferencesController {

    @Autowired
    private UsersService usersService;

    @PutMapping
    public ResponseEntity<?> updateUserPreferences(@RequestBody PreferencesDTO preferencesDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Users loggedInUser = usersService.findUserByName(username);
        usersService.setPreferencesByIds(preferencesDTO.getPreferences(), loggedInUser);

        return ResponseEntity.ok("Preferences updated successfully.");
    }


    @GetMapping
    public ResponseEntity<?> getUserPreferences() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Users loggedInUser = usersService.findUserByName(username);
        List<PreferencesResponseDTO> preferencesDTO = usersService.getPreferencesForUser(loggedInUser);
        return ResponseEntity.ok(preferencesDTO);
    }
}
