package com.RevatureProjects.Project1.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import com.RevatureProjects.Project1.entities.Reimbursement;
import com.RevatureProjects.Project1.service.ReimbursementService;

@Controller
public class ReimbController {
        @Autowired
        private ReimbursementService reimbursementService;


    @GetMapping("/reimbursements/{userId}")
    public @ResponseBody ResponseEntity<List<Reimbursement>>getAllReinbursementById(){
      List<Reimbursement> r =  reimbursementService.getAllReimbursementsByUserId();
       
        return ResponseEntity.status(200).body(r);
    }
    
    @GetMapping("/reimbursements/{userId}/pending")
    public @ResponseBody ResponseEntity<List<Reimbursement>>getAllPendingReinbursementById(@PathVariable Integer userId){
      List<Reimbursement> r =  reimbursementService.getPendingTickets(userId);
       
        return ResponseEntity.status(200).body(r);
    }

    @PostMapping("/reimbursement")
    public @ResponseBody ResponseEntity<Reimbursement> createReinbursement(@RequestBody Reimbursement reimbursement){
        
        try {
            Reimbursement re = reimbursementService.createReimbursement(reimbursement);
            return ResponseEntity.ok(re);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        
    }
    @GetMapping("/reimbursements")
    public @ResponseBody ResponseEntity<List<Reimbursement>>getAllReinbursements(){
      List<Reimbursement> r =  reimbursementService.getAllPendingReimbursements();
       
        return ResponseEntity.status(200).body(r);
    }

    @GetMapping("/reimbursements/pending")
    public @ResponseBody ResponseEntity<List<Reimbursement>>getAllPendingReinbursementById(){
      List<Reimbursement> r =  reimbursementService.getAllPendingReimbursements();
       
        return ResponseEntity.status(200).body(r);
    }

    @PatchMapping("/reimbursement/status/{reimbId}")
    public @ResponseBody ResponseEntity<Reimbursement> updateStatus(@RequestBody String status, @PathVariable Integer reimbId, @RequestHeader String token ){
       // String authToken = token.substring(21);
     Optional<Reimbursement> re = Optional.ofNullable(reimbursementService.updateStatus(token,reimbId, status));
        if(re.isPresent()){
            return ResponseEntity.ok().body(re.get());
        }
        return ResponseEntity.status(401).body(null);
    /* try {
      if (reimbId == null || reimbId <= 0) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
            return ResponseEntity.ok().body(r);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } */
        
    }
}
