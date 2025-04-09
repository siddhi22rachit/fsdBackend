package com.FSDProject.FSD.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FSDProject.FSD.entity.SupervisorEntity;
import com.FSDProject.FSD.repository.SupervisorRepo;

@Service
public class SupervisorService {
    
    @Autowired
    private SupervisorRepo supervisorRepo; 

    public SupervisorEntity saveSupervisor(SupervisorEntity SupervisorEntity) {  
        return supervisorRepo.save(SupervisorEntity);  
    }

    public List<SupervisorEntity> getAllSupervisor() {
        return supervisorRepo.findAll(); 
    }

    public Optional<SupervisorEntity> getSupervisorById(Long id) {
        return supervisorRepo.findById(id); 
    }

    public String deleteSupervisorById(Long id) {  
        Optional<SupervisorEntity> SupervisorEntity = supervisorRepo.findById(id);
        if (SupervisorEntity.isPresent()) {
          supervisorRepo.deleteById(id);
            return "Deleted successfully";
        } else {
            return "Supervisor not found with ID: " + id;
        }
    }
}

