package com.RevatureProjects.Project1.controllers;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.RevatureProjects.Project1.entities.User;
import com.RevatureProjects.Project1.service.UserService;
import com.RevatureProjects.Project1.service.jwtService;


@Controller
public class UserController {
    
       
        private UserService userService;
        private jwtService jwtService;
        
        
    @PostMapping("/register")
    public @ResponseBody ResponseEntity<User> registerUser (@RequestBody User user) {

        try {
            User u = userService.registerUser(user);
            return ResponseEntity.ok(u);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    @PostMapping("/login")
    public @ResponseBody ResponseEntity<String> loginUser(@RequestBody User user){
        
        try {
            User us = userService.loginUser(user.getUsername(), user.getPassword());
            String token = jwtService.generateToken(us);
            return ResponseEntity.ok(token);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        
    }

    @GetMapping("/users")
    public @ResponseBody ResponseEntity<List<User>>getAllUsers(){
    List<User> u =  userService.getAllUsers();
        return ResponseEntity.status(200).body(u);
    }

    @DeleteMapping("/users/{userId}")
    public @ResponseBody ResponseEntity<String> deleteUserById(@PathVariable Integer userId){
    try{
        String u = userService.deleteUserById(userId);
        return ResponseEntity.status(200).body(u);
    }
        catch(IllegalArgumentException e){ 
            return ResponseEntity.notFound().build();
        } 
    }
}
