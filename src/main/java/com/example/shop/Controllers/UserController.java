package com.example.shop.Controllers;


import com.example.shop.Models.User;
import com.example.shop.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("")
    public List<User> getUsers(){

        return userRepository.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") int id){
        return userRepository.getUserById(id);
    }
    @PostMapping("")
    public int addUser(@RequestBody LinkedList<User> users){
        return userRepository.addUsers(users);
    }

    @DeleteMapping("{id}")
    public int deleteUserById(@PathVariable("id") int id){
        return userRepository.deleteUserById(id);
    }
    @PutMapping("/{id}")
    public int updateUser(@PathVariable("id") int id, @RequestBody User updateUser){
        User user = userRepository.getUserById(id);
        if(user != null) {
            updateUser.setId(id);
            userRepository.updateUser(updateUser);
            return 1;
        } else {
            return -1;
        }
    }

    @PatchMapping("/{id}")
    public int patchUser(@PathVariable("id") int id, @RequestBody User updateUser){
        User user = userRepository.getUserById(id);
        if(user != null) {
            if (updateUser.getName() == null) {
                updateUser.setName(user.getName());
            }
            if (updateUser.getSurName() == null) {
                updateUser.setSurName(user.getSurName());
            }
            if (updateUser.getEmail() == null) {
                updateUser.setEmail(user.getEmail());
            }
            if (updateUser.getPassword() == null) {
                updateUser.setPassword(user.getPassword());
            }
            if (updateUser.getRole() == 0) {
                updateUser.setRole(user.getRole());
            }
            updateUser.setId(id);
            userRepository.patchUser(updateUser);
            return 1;
        } else {
            return -1;
        }
    }
}
