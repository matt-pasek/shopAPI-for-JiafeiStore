package com.example.shop.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class User {
    private int id;
    private String name;
    private String surName;
    private String email;
    private String password;
    private int role;
}
