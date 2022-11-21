package com.example.shop.Repositories;

import com.example.shop.Models.Order;
import com.example.shop.Models.OrderContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Order> getAllOrders() {
        return jdbcTemplate.query("select * from orders", BeanPropertyRowMapper.newInstance(Order.class));
    }

    public List<OrderContent> getOrderContentById(int id) {
        return jdbcTemplate.query("select * from order_content where order_id = ?", BeanPropertyRowMapper.newInstance(OrderContent.class), id);
    }

    public Order getOrderById(int id) {
        var order = jdbcTemplate.queryForObject("select * from order where id = ?", BeanPropertyRowMapper.newInstance(Order.class), id);
        var content = getOrderContentById(id);
        return new Order(order.getId(), order.getUser_id(), order.getDate(), content);
    }

    public int addOrder(Order order) {
        jdbcTemplate.update("insert into order (user_id, date) values (?,?)",
                order.getUser_id(),
                order.getDate()
        );
        for (int i = 0; i < order.getProducts().size(); i++) {
            var product = order.getProducts().get(i);
            jdbcTemplate.update("insert into order_content (order_id, product_id, quantity) values (?,?,?)",
                    order.getId(),
                    product.getProduct_id(),
                    product.getQuantity()
            );
        }
        return 0;
    }

    public int deleteOrderById(int id){
        jdbcTemplate.update("delete from order_content where order_id = ?", id);
        jdbcTemplate.update("delete from order where id = ?", id);
        return 0;
    }
}
