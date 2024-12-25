package com.RevatureProjects.Project1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RevatureProjects.Project1.entities.Reimbursement;
import com.RevatureProjects.Project1.entities.User;
import com.RevatureProjects.Project1.repositories.ReimbursementRepository;
import com.RevatureProjects.Project1.repositories.UserRepository;

@Service
public class ReimbursementService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReimbursementRepository reimbursementRepository;

    @Autowired
    private JwtService jwtService;

    public ReimbursementService(ReimbursementRepository reinbursementRepository, UserRepository userRepository){
        this.reimbursementRepository = reinbursementRepository;
        this.userRepository = userRepository;
    }
    
    public List<Reimbursement> getAllReimbursementsByUserId(){
        User user = new User();
        return reimbursementRepository.findAllByUserId(user.getUserId());

    }

     public List<Reimbursement> getPendingTickets( Integer userId){
        Reimbursement r = new Reimbursement();
        if(!r.getStatus().equals("pending")){
            return null;
        }
        return reimbursementRepository.findAllByUserId(userId);
    }

    public Reimbursement createReimbursement(Reimbursement reimbursement){
        if(reimbursement.getDescription().isEmpty()){
            throw new IllegalArgumentException("Invalid input");
        }
        reimbursement.setStatus("pending");
        return reimbursementRepository.save(reimbursement);
    }

    public List<Reimbursement> getAllReimbursements(){
        User user = new User();
        if(!user.getRole().equals("manager")){
            return null;
        }
        return reimbursementRepository.findAll();
    }

    public List<Reimbursement> getAllPendingReimbursements(){
        User user = new User();
        Reimbursement reimbursement = new Reimbursement();
        if(!user.getRole().equals("manager") || !reimbursement.getStatus().equals("pending")){
            return null;
        }
        return reimbursementRepository.findAll();
    }

    public Reimbursement updateStatus(String token, Integer reimbId, String status){
     
     User user = jwtService.decodeToken(token);
     if(user == null || user.getRole() !=null ){
       // return null;
        throw new SecurityException("Unauthorized");
     }
    /*  Optional<Reimbursement> re = reimbursementRepository.findById(reimbId);
     if(re.isPresent()){   getRole().equals("manager")
         Reimbursement r = re.get();
         r.setStatus(status);
         return reimbursementRepository.save(r);
        } */
        Reimbursement r = reimbursementRepository.findById(reimbId).orElseThrow(()-> new IllegalArgumentException("Invalid input"));
       r.setStatus(status);
        reimbursementRepository.flush();
        return reimbursementRepository.save(r);
    }
   
}
