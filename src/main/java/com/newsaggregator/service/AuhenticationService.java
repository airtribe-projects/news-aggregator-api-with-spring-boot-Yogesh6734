package com.newsaggregator.service;

import com.newsaggregator.DTO.UsersRequestDTO;
import com.newsaggregator.Entity.Users;
import com.newsaggregator.Entity.VerificationToken;
import com.newsaggregator.repository.UsersRepository;
import com.newsaggregator.repository.VerificationRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class AuhenticationService implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private VerificationRepository verificationRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Users registerUser(UsersRequestDTO usersRequestDTO) {
        Users users= new Users();
        users.setEmail(usersRequestDTO.getEmail());
        users.setPassword(passwordEncoder.encode(usersRequestDTO.getPassword()));
        users.setRole("USER");
        users.setEnabled(false);
        users.setName(usersRequestDTO.getName());
        return usersRepository.save(users);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users= usersRepository.findByName(username);
        if(users==null){
            throw new UsernameNotFoundException("User Not found");
        }

        return new org.springframework.security.core.userdetails.User(
                users.getName(), users.getPassword(),
                java.util.Collections.emptyList());

    }

    public void saveToken(String generatedToken, Users users) {
        VerificationToken verificationToken= new VerificationToken();
        verificationToken.setToken(generatedToken);
        verificationToken.setUsers(users);
        verificationToken.setExpiryDate(new java.util.Date(System.currentTimeMillis()+1000*60*60*24));

        verificationRepository.save(verificationToken);
    }

    public boolean verifyToken(String token) {
        VerificationToken verificationToken= verificationRepository.findByToken(token);
        if(verificationToken ==null){
            return  false;
        }
        return verificationToken.getExpiryDate().getTime() >= System.currentTimeMillis();
    }

    public void enableUser(String token) {
        VerificationToken verificationToken= verificationRepository.findByToken(token);
        if(verificationToken==null){
            return;
        }
        Users user= verificationToken.getUsers();
        user.setEnabled(true);
        usersRepository.save(user);

    }
}
