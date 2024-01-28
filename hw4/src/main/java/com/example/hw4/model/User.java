package com.example.hw4.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {

    String name;

    public User(String name) {
        this.name = name;
    }
}
