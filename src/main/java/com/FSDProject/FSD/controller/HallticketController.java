package com.FSDProject.FSD.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.FSDProject.FSD.entity.HallticketEntity;
import com.FSDProject.FSD.service.HallticketService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin
@RestController
@RequestMapping("/hallticket")
public class HallticketController {

    private static final Logger logger = LoggerFactory.getLogger(HallticketController.class);

    @Autowired
    private HallticketService hallticketService;

    @PostMapping("/create")
    public ResponseEntity<String> createHallticket(@RequestBody HallticketEntity hallticketEntity) {
        logger.info("Request received to create a hallticket with name: {}", hallticketEntity.getName());
        try {
            hallticketService.saveHallticket(hallticketEntity);
            logger.info("Hallticket created successfully with ID: {}", hallticketEntity.getHallticketID());
            return new ResponseEntity<>("Hallticket created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating hallticket: {}", e.getMessage());
            return new ResponseEntity<>("Error creating hallticket: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<HallticketEntity>> getAllHalltickets() {
        logger.info("Request received to get all halltickets");
        try {
            List<HallticketEntity> halltickets = hallticketService.getAllHalltickets();
            if (halltickets.isEmpty()) {
                logger.warn("No halltickets found");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            logger.info("Returning list of {} halltickets", halltickets.size());
            return new ResponseEntity<>(halltickets, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching halltickets: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteHallticket(@PathVariable Long id) {
        logger.info("Request received to delete hallticket with ID: {}", id);
        try {
            Optional<HallticketEntity> hallticketData = hallticketService.getHallticketById(id);
            if (hallticketData.isPresent()) {
                hallticketService.deleteHallticketById(id);
                logger.info("Hallticket deleted successfully with ID: {}", id);
                return new ResponseEntity<>("Hallticket deleted successfully", HttpStatus.OK);
            } else {
                logger.warn("Hallticket not found with ID: {}", id);
                return new ResponseEntity<>("Hallticket not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error deleting hallticket with ID {}: {}", id, e.getMessage());
            return new ResponseEntity<>("Error deleting hallticket: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
