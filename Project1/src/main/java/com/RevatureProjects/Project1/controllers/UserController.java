package com.RevatureProjects.Project1.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.RevatureProjects.Project1.entities.User;
import com.RevatureProjects.Project1.service.JwtService;
import com.RevatureProjects.Project1.service.UserService;


@RestController
public class UserController {
    
        @Autowired
        private UserService userService;

        @Autowired
        private JwtService jwtService;
        
    @PostMapping("/register")
    public @ResponseBody ResponseEntity<User> registerUser (@RequestBody User user) {
        Optional<User> u = Optional.ofNullable(userService.registerUser(user));
        try{
            if(u != null){
            return ResponseEntity.ok().body(u.get());
        }
       
       }catch(DataIntegrityViolationException | IllegalArgumentException e){
        return ResponseEntity.status(409).build();
    }
        return ResponseEntity.status(409).build();
    }
    
    @PostMapping("/login")
    public @ResponseBody ResponseEntity<String> loginUser(@RequestBody User user){
       // LoginDTO loginDTO = new LoginDTO();
        //loginDTO.setUsername(user.getUsername());
        //loginDTO.setPassword(user.getPassword());
        Optional<String> us = Optional.ofNullable(userService.loginUser(user));
        //try {
            if(us.isPresent()){
                return ResponseEntity.ok(us.get());
            }
           // String token = jwtService.generateToken(us);
          // return ResponseEntity.ok().body(us.get());
       // }//catch(NullPointerException | IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
       // }
        
    }
 
    @GetMapping("/users")
    public @ResponseBody ResponseEntity<List<User>>getAllUsers(){
    List<User> u =  userService.getAllUsers();
        return ResponseEntity.status(200).body(u);
    }

    @DeleteMapping("/users/{userId}")
    public @ResponseBody ResponseEntity<Integer> deleteUserById(@PathVariable Integer userId){
    try{
        int u = userService.deleteUserById(userId);
        return ResponseEntity.status(200).body(null);
    }
        catch(IllegalArgumentException e){ 
            return ResponseEntity.notFound().build();
        } 
    } 
}
