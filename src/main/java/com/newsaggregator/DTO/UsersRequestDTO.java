package com.newsaggregator.DTO;

import lombok.Data;

@Data
public class UsersRequestDTO {

    private String email;
    private String password;
    private String name;
}
