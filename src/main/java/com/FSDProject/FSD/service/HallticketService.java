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

    // Save Hallticket
    public HallticketEntity saveHallticket(HallticketEntity hallticketEntity) {
        return hallticketRepo.save(hallticketEntity);
    }

    // Get all Halltickets
    public List<HallticketEntity> getAllHalltickets() {
        return hallticketRepo.findAll();
    }

    // Get Hallticket by ID
    public Optional<HallticketEntity> getHallticketById(Long id) {
        return hallticketRepo.findById(id);
    }

    // Delete Hallticket by ID
    public String deleteHallticketById(Long id) {
        hallticketRepo.deleteById(id);
        return "Hallticket deleted successfully";
    }

    // Update Hallticket
    public HallticketEntity updateHallticket(HallticketEntity hallticketEntity) {
        return hallticketRepo.save(hallticketEntity);
    }
}
