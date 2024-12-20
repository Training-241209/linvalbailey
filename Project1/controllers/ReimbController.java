package com.RevatureProjects.Project1.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.RevatureProjects.Project1.entities.Reinbursement;
import com.RevatureProjects.Project1.service.ReinbursementService;

@Controller
public class ReimbController {
    
        private ReinbursementService reinbursementService;


    @GetMapping("/reinbursements/{userId}")
    public @ResponseBody ResponseEntity<List<Reinbursement>>getAllReinbursementById(){
      List<Reinbursement> r =  reinbursementService.getAllReinbursementsByUserId();
       
        return ResponseEntity.status(200).body(r);
    }
    
    @GetMapping("/reinbursements/{userId}/pending")
    public @ResponseBody ResponseEntity<List<Reinbursement>>getAllPendingReinbursementById(@PathVariable Integer userId){
      List<Reinbursement> r =  reinbursementService.getPendingTickets(userId);
       
        return ResponseEntity.status(200).body(r);
    }

    @PostMapping("/reinbursement")
    public @ResponseBody ResponseEntity<Reinbursement> createReinbursement(@RequestBody Reinbursement reinbursement){
        
        try {
            Reinbursement re = reinbursementService.createReinbursement(reinbursement);
            return ResponseEntity.ok(re);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        
    }
    @GetMapping("/reinbursements")
    public @ResponseBody ResponseEntity<List<Reinbursement>>getAllReinbursements(){
      List<Reinbursement> r =  reinbursementService.getAllReinbursements();
       
        return ResponseEntity.status(200).body(r);
    }

    @GetMapping("/reinbursements/pending")
    public @ResponseBody ResponseEntity<List<Reinbursement>>getAllPendingReinbursementById(){
      List<Reinbursement> r =  reinbursementService.getAllPendingReinbursements();
       
        return ResponseEntity.status(200).body(r);
    }

    @PatchMapping("/reinbursement/status")
    public @ResponseBody ResponseEntity<Reinbursement> updateStatus(@PathVariable Integer userId, @RequestBody String status){
        try {
     Reinbursement r = reinbursementService.updateStatus(userId, status);
            if(r == null){
                return ResponseEntity.status(400).build();
            }
            return ResponseEntity.ok(r);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        
    }
}
