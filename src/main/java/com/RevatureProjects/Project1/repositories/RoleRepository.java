package com.RevatureProjects.Project1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RevatureProjects.Project1.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    
}
