package com.example.shop.Controllers;

import com.example.shop.Models.Order;
import com.example.shop.Models.OrderContent;
import com.example.shop.Models.Product;
import com.example.shop.Repositories.OrderRepository;
import com.example.shop.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    OrderRepository orderRepository;

    @GetMapping("")
    public List<Order> getOrders(){
        return orderRepository.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable("id") int id){
        return orderRepository.getOrderById(id);
    }

    @PostMapping("")
    public int addOrder(@RequestBody Order order){
        return orderRepository.addOrder(order);
    }

    @DeleteMapping("{id}")
    public int deleteOrderById(@PathVariable("id") int id){
        return orderRepository.deleteOrderById(id);
    }
}
