package com.RevatureProjects.Project1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.RevatureProjects.Project1.entities.Reinbursement;
import com.RevatureProjects.Project1.entities.User;
import com.RevatureProjects.Project1.repositories.ReinbursementRepository;
import com.RevatureProjects.Project1.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReinbursementService {
    private UserRepository userRepository;
    private ReinbursementRepository reinbursementRepository;

    public ReinbursementService(ReinbursementRepository reinbursementRepository, UserRepository userRepository){
        this.reinbursementRepository = reinbursementRepository;
        this.userRepository = userRepository;
    }
    
    public List<Reinbursement> getAllReinbursementsByUserId(){
        User user = new User();
        return reinbursementRepository.findAllByUserId(user.getUserId());

    }

     public List<Reinbursement> getPendingTickets( Integer userId){
        Reinbursement r = new Reinbursement();
        if(!r.getStatus().equals("pending")){
            return null;
        }
        return reinbursementRepository.findAllByUserId(userId);
    }

    public Reinbursement createReinbursement(Reinbursement reinbursement){
        if(reinbursement.getDescription().isEmpty()){
            throw new IllegalArgumentException("Invalid input");
        }

        return reinbursementRepository.save(reinbursement);
    }

    public List<Reinbursement> getAllReinbursements(){
        User user = new User();
        if(!user.getRole().equals("manager")){
            return null;
        }
        return reinbursementRepository.findAll();
    }

    public List<Reinbursement> getAllPendingReinbursements(){
        User user = new User();
        Reinbursement reinbursement = new Reinbursement();
        if(!user.getRole().equals("manager") || !reinbursement.getStatus().equals("pending")){
            return null;
        }
        return reinbursementRepository.findAll();
    }

    public Reinbursement updateStatus( Integer userId, String status){
        User u = new User();
        if(!u.getRole().equals("manager")){
            return null;
        }
        Reinbursement r = reinbursementRepository.findById(userId).orElse(null);
        r.setStatus(status);
        return reinbursementRepository.save(r);
    }
   
}
