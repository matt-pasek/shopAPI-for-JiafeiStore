package com.example.shop.Repositories;

import com.example.shop.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository

public class ProductRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Product> getAllProducts() {
        return jdbcTemplate.query("select * from product", BeanPropertyRowMapper.newInstance(Product.class));
    }
    public Product getProductById(int id) {
        return jdbcTemplate.queryForObject("select * from product where id = ?", BeanPropertyRowMapper.newInstance(Product.class), id);
    }


    public int addProduct(LinkedList<Product> prods) {
        for (Product prod : prods) {
            jdbcTemplate.update("insert into product(name,price,description,category,image) values (?,?,?,?,?)",
                    prod.getName(),
                    prod.getPrice(),
                    prod.getDescription(),
                    prod.getCategory(),
                    prod.getImage()
            );
        }
        return 0;
    }

    public int deleteProductById(int id){
        return jdbcTemplate.update("delete from product where id = ?", id);
    }

    public void updateProduct(Product prod) {
        jdbcTemplate.update("Update product set name=?,price=?,description=?,category=?,image=? where id=?",
                prod.getName(),
                prod.getPrice(),
                prod.getDescription(),
                prod.getCategory(),
                prod.getImage(),
                prod.getId()
        );
    }

    public void patchProduct(Product prod) {
        jdbcTemplate.update("Update product set name=?,price=?,description=?,category=?,image=? where id=?",
                prod.getName(),
                prod.getPrice(),
                prod.getDescription(),
                prod.getCategory(),
                prod.getImage(),
                prod.getId()
        );
    }
}
