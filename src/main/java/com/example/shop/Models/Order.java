package com.example.shop.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Order {
    private int id;
    private int user_id;
    private Date date;
    private List<OrderContent> products;
}
