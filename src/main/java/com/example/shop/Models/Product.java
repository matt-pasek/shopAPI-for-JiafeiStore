package com.example.shop.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Product {
    private int id;
    private String name;
    private float price;
    private String description;
    private int category;
    private String image;
}
