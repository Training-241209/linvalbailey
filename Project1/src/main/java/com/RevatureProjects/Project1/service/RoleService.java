package com.RevatureProjects.Project1.service;

import org.springframework.stereotype.Service;

import com.RevatureProjects.Project1.entities.Role;
import com.RevatureProjects.Project1.repositories.RoleRepository;
;

@Service
public class RoleService {
    private Role role;
    private RoleRepository roleRepository;
    

    public RoleService(RoleRepository roleRepository){
        this.roleRepository=roleRepository;
    }


   /*  public Role setRole(String role){
        if(role.equals(null){

        })
    } */
}
