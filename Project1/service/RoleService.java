package com.RevatureProjects.Project1.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.RevatureProjects.Project1.entities.Role;
import com.RevatureProjects.Project1.repositories.RoleRepository;;

@Service
@Transactional
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
