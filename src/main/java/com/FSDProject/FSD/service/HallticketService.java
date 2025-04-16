package com.FSDProject.FSD.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FSDProject.FSD.entity.HallticketEntity;
import com.FSDProject.FSD.repository.HallticketRepo;

@Service
public class HallticketService {
    
    @Autowired
    private HallticketRepo hallticketRepo; 

    public HallticketEntity saveHallticket(HallticketEntity hallticketEntity) {  
        return hallticketRepo.save(hallticketEntity);  
    }

    public List<HallticketEntity> getAllHalltickets() {
        return hallticketRepo.findAll(); 
    }

    public Optional<HallticketEntity> getHallticketById(Long id) {
        return hallticketRepo.findById(id); 
    }

    public String deleteHallticketById(Long id) {  
        Optional<HallticketEntity> hallticketEntity = hallticketRepo.findById(id);
        if (hallticketEntity.isPresent()) {
            hallticketRepo.deleteById(id);
            return "Deleted successfully";
        } else {
            return "Hallticket not found with ID: " + id;
        }
    }
}
