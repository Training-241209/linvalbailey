package com.RevatureProjects.Project1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RevatureProjects.Project1.entities.Reimbursement;

@Repository
public interface ReimbursementRepository extends JpaRepository<Reimbursement,Integer>{

    List<Reimbursement> findAllByUserId(Integer userId);
   // @NonNull @Override
  // Optional<Reinbursement> findById(Integer userId);

   // Reimbursement findByUsername(String username);
    
}
