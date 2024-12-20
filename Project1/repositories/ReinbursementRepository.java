package com.RevatureProjects.Project1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RevatureProjects.Project1.entities.Reinbursement;

@Repository
public interface ReinbursementRepository extends JpaRepository<Reinbursement,Integer>{

    List<Reinbursement> findAllByUserId(Integer userId);
   // @NonNull @Override
  // Optional<Reinbursement> findById(Integer userId);
    
}
