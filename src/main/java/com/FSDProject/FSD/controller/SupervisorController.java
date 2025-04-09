package com.FSDProject.FSD.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.FSDProject.FSD.entity.SupervisorEntity;
import com.FSDProject.FSD.service.SupervisorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin
@RestController
@RequestMapping("/supervisor")
public class SupervisorController {

    private static final Logger logger = LoggerFactory.getLogger(SupervisorController.class);

    @Autowired
    private SupervisorService supervisorService;

    @PostMapping("/create")
    public ResponseEntity<String> createSupervisor(@RequestBody SupervisorEntity supervisorEntity) {
        logger.info("Request received to create a supervisor with name: {}", supervisorEntity.getName());
        try {
          supervisorService.saveSupervisor(supervisorEntity);
            logger.info("Supervisor created successfully with ID: {}",supervisorEntity.getSupervisorID());
            return new ResponseEntity<>("Supervisor created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating Supervisor: {}", e.getMessage());
            return new ResponseEntity<>("Error creating Supervisor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<SupervisorEntity>> getAllSupervisor() {
        logger.info("Request received to get all Supervisor");
        try {
            List<SupervisorEntity> supervisor = supervisorService.getAllSupervisor();
            if (supervisor.isEmpty()) {
                logger.warn("No Supervisor found");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            logger.info("Returning list of {} Supervisor", supervisor.size());
            return new ResponseEntity<>(supervisor, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching Supervisor: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSupervisor(@PathVariable Long id) {
        logger.info("Request received to delete Supervisor with ID: {}", id);
        try {
            Optional<SupervisorEntity> supervisorData = supervisorService.getSupervisorById(id);
            if (supervisorData.isPresent()) {
              supervisorService.deleteSupervisorById(id);
                logger.info("Supervisor deleted successfully with ID: {}", id);
                return new ResponseEntity<>("Supervisor deleted successfully", HttpStatus.OK);
            } else {
                logger.warn("Supervisor not found with ID: {}", id);
                return new ResponseEntity<>("Supervisor not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error deleting Supervisor with ID {}: {}", id, e.getMessage());
            return new ResponseEntity<>("Error deleting Supervisor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
