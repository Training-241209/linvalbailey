package com.RevatureProjects.Project1.service;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.RevatureProjects.Project1.entities.User;
import com.RevatureProjects.Project1.repositories.ReimbursementRepository;
import com.RevatureProjects.Project1.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReimbursementRepository reimbursementRepository;

    @Autowired
    private JwtService jwtService;

    public UserService(UserRepository userRepository, ReimbursementRepository reimbursementRepository){
        this.reimbursementRepository = reimbursementRepository;
        this.userRepository = userRepository;
    }

    public User registerUser(User user){
        if(user.getUsername().isEmpty() || user.getPassword().length() < 5){
            throw new IllegalArgumentException("Invalid Input");
           // return null;
        }

        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            throw new DataIntegrityViolationException("Username already exists");
           //return null;
        }
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        user.setRole( "employee");
        user.getFirstName();
        user.getLastName();
        userRepository.flush();
        return userRepository.save(user);
    }

    public String loginUser(User user){
        Optional<User> us = userRepository.findByUsername(user.getUsername());
        if (BCrypt.checkpw(user.getPassword(), us.get().getPassword())){
            //us.get().getPassword();
            String jt = jwtService.generateToken(user);
           // user.getRole();
            return jt;
        }
       
        return  null;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public int deleteUserById(Integer userId){
        User u = new User();
        if(u.getRole().equals("manager")){
            userRepository.deleteById(userId);
            return 1;
        }
        if(userId == null){
            throw new IllegalArgumentException("User does not exist");
        }
       return 0;
    } 
}

