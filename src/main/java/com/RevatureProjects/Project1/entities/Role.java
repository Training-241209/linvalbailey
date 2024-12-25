package com.RevatureProjects.Project1.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="Role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column
    private String role;

    public Role (){

    }

    public Role (Integer userId){
        this.userId = userId;
    }

    public Role (Integer userId, String role){
        this.userId=userId;
        this.role=role;
    }

     public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer userId){
        this.userId =userId;
    }


    public String getRole(){
        return role;
    }



    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Role other = (Role) obj;
        if(userId == null){
            if(other.userId != null)
                return false;
        }else if(!userId.equals(other.userId))
            return false;
        if(role == null){
            if(other.role != null)
                return false;
        }else if(!role.equals(other.role))
            return false;  
        return true; 
    }

     @Override
public int hashCode() {
    return Objects.hash(userId,role);
}
}
