package com.RevatureProjects.Project1.service;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.RevatureProjects.Project1.entities.User;
import com.RevatureProjects.Project1.repositories.ReinbursementRepository;
import com.RevatureProjects.Project1.repositories.UserRepository;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private ReinbursementRepository reinbursementRepository;

    public UserService(UserRepository userRepository, ReinbursementRepository reinbursementRepository){
        this.reinbursementRepository = reinbursementRepository;
        this.userRepository = userRepository;
    }

    public User registerUser(User user){
        if(user.getUsername().isEmpty() || user.getPassword().length() < 5){
            throw new IllegalArgumentException("Invalid Input");
        }

        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            throw new DataIntegrityViolationException("Username already exists");
        }
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        user.setRole( "employee");

        return userRepository.save(user);
    }

    public User loginUser(String username, String password){
        return userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));
        
    }

    public List<User> getAllUsers(){
        User u = new User();
        if(!u.getRole().equals("manager")){
            throw new DataIntegrityViolationException("Not authorized");
        }
        return userRepository.findAll();
    }

    public String deleteUserById(Integer userId){
        User u = new User();
        if(u.getRole().equals("manager")){
            userRepository.deleteById(userId);
        }
        if(userId == null){
            throw new IllegalArgumentException("User does not exist");
        }
       return "User successfully deleted";
    }
}

