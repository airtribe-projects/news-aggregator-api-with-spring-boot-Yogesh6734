package com.newsaggregator.controller;

import com.newsaggregator.DTO.UsersRequestDTO;
import com.newsaggregator.Entity.Users;
import com.newsaggregator.service.AuhenticationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UsersController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuhenticationService auhenticationService;
    @PostMapping("/register")
    public Users registerUser(@RequestBody UsersRequestDTO usersRequestDTO){
          Users registeredUser=  auhenticationService.registerUser(usersRequestDTO);
          String generatedToken= UUID.randomUUID().toString();
          String applicationUrl= "http://localhost:8080/api/verifyToken?token="+generatedToken;
        System.out.println(applicationUrl);
        auhenticationService.saveToken(generatedToken,registeredUser);
          return registeredUser;
    }

    @PostMapping("/verifyToken")
    public String verifyToken(@RequestParam("token") String token){
        boolean isTokenValid= auhenticationService.verifyToken(token);
        if(isTokenValid){
            auhenticationService.enableUser(token);
            return "Token verified successfully";
        }

        return "Token verification failed";

    }

    @PostMapping("/signin")
    public String siginUser(@RequestParam("name") String name, @RequestParam("password") String password, HttpServletRequest request){
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(name, password);

        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            request.getSession(true).setAttribute(
                    HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                    SecurityContextHolder.getContext()
            );

            return "Authentication successful";
        } catch (AuthenticationException ex) {
            return "Authentication failed: " + ex.getMessage();
        }
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

}
