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
@Table(name = "users")
public class User {
    //@OneToMany
    @Column(name="userId")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    
    @Column(name = "username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="role")
    private String role;

    @Column(name="firstname")
    private String firstname;

    @Column(name="lastname")
    private String lastname;

    public User(){

    }

    public User(Integer userId,String username ){
        this.username=username;
        this.userId=userId;

    }
   

    public User (String username, String password){
        this.username = username;
        this.password = password;
    }

    public User (String username, String password, String role){
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer userId){
        this.userId =userId;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword( String password){
        this.password = password;
    }

    public String getRole(){
        return role;
    }

    public void setRole(String role){
     this.role = role;   
    }

    public String getFirstName(){
        return firstname;
    }

    public void setFirstName(String firstname){
        this.firstname = firstname;
    }

    public String getLastName(){
        return lastname;
    }

    public void setLastName(String lastname){
        this.lastname = lastname;
    }


    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if(userId == null){
            if(other.userId != null)
                return false;
        }else if(!userId.equals(other.userId))
            return false;
        if(username == null){
            if(other.username != null)
                return false;
        }else if(!username.equals(other.username))
            return false;
        if(password == null){
            if(other.password != null)
                return false;
        }else if(!password.equals(other.password))
            return false;
        if(firstname == null){
            if(other.firstname != null)
                return false;
        }else if(!firstname.equals(other.firstname))
            return false;
        if(lastname == null){
            if(other.lastname != null)
                return false;
        }else if(!lastname.equals(other.lastname))
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
    return Objects.hash(userId, firstname, lastname, role, username,password);
}
}
