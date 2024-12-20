package com.RevatureProjects.Project1.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RevatureProjects.Project1.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameAndPassword(String username, String password);
    //String deleteById(Long userId);
}