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
@Table(name="reinburement")
public class Reinbursement {
    
    @Column(name="reimbId")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reimbId;

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "status")
    private String status;

    
   @Column(name = "userId")
    private Integer userId;

    public Reinbursement(){

    }

    public Reinbursement( Integer reimbId, String status){
        this.reimbId = reimbId;
        this.status = status;

    }

    public Reinbursement( Integer reimbId, String status,Integer amount){
        this.reimbId = reimbId;
        this.status = status;
        this.amount= amount;

    }

    public Reinbursement( Integer reimbId, String status,Integer amount, String description){
        this.reimbId = reimbId;
        this.status = status;
        this.amount= amount;
        this.description = description;

    }

    public Integer getReimbId(){
        return reimbId;
    }

    public void setReimbId(Integer reimbId){
        this.reimbId = reimbId;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public Integer getAmount(){
        return amount;
    }

    public void setAmount(Integer amount){
        this.amount = amount;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer userId){
        this.userId = userId;
    }


    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Reinbursement other = (Reinbursement) obj;
        if(userId == null){
            if(other.userId != null)
                return false;
        }else if(!userId.equals(other.userId))
            return false;
        if(description == null){
            if(other.description != null)
                return false;
        }else if(!description.equals(other.description))
            return false;
        if(amount == null){
            if(other.amount != null)
                return false;
        }else if(!amount.equals(other.amount))
            return false;
        if(reimbId == null){
            if(other.reimbId != null)
                return false;
        }else if(!reimbId.equals(other.reimbId))
            return false;
        if(status == null){
            if(other.status != null)
                return false;
        }else if(!status.equals(other.status))
            return false; 
        return true; 
    }

    @Override
public int hashCode() {
    return Objects.hash(userId, description, amount, reimbId, status);
}
}
