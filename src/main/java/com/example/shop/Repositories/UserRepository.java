package com.example.shop.Repositories;

import com.example.shop.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository

public class UserRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<User> getAllUsers() {
        return jdbcTemplate.query("select * from user", BeanPropertyRowMapper.newInstance(User.class));
    }

    public User getUserById(int id) {
        return jdbcTemplate.queryForObject("select * from user where id = ?", BeanPropertyRowMapper.newInstance(User.class), id);
    }


    public int addUsers(LinkedList<User> users) {
        for (User user : users) {
            jdbcTemplate.update("insert into user(name,surname,email,role, password) values (?,?,?,?,?)",
                    user.getName(),
                    user.getSurName(),
                    user.getEmail(),
                    user.getRole(),
                    user.getPassword()
            );
        }
        return 0;
    }

    public int deleteUserById(int id){
        return jdbcTemplate.update("delete from user where id = ?", id);
    }

    public int updateUser(User user) {
        return jdbcTemplate.update("Update user set name=?,surname=?,email=?,role=?, password=? where id=?",
                user.getName(),
                user.getSurName(),
                user.getEmail(),
                user.getRole(),
                user.getPassword(),
                user.getId()
                );
    }

    public int patchUser(User user) {
        return jdbcTemplate.update("Update user set name=?,surname=?,email=?,role=?, password=? where id=?",
                user.getName(),
                user.getSurName(),
                user.getEmail(),
                user.getRole(),
                user.getPassword(),
                user.getId()
        );
    }
}
