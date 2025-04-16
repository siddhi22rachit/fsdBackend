package com.FSDProject.FSD.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.FSDProject.FSD.entity.HallticketEntity;
import com.FSDProject.FSD.service.HallticketService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/hallticket")
public class HallticketController {
    private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);
    @Autowired
    private HallticketService hallticketService;

    // Create a new Hallticket
    @PostMapping("/create")
    public ResponseEntity<String> createHallticket(@RequestBody HallticketEntity hallticketEntity) {
        logger.info("Request received to create an hallticket with name: {}", hallticketEntity.getStudentName());
        hallticketService.saveHallticket(hallticketEntity);
        return new ResponseEntity<>("Hallticket created successfully", HttpStatus.CREATED);
    }

    // Get all Halltickets      
    @GetMapping("/all")    
    public ResponseEntity<List<HallticketEntity>> getAllHalltickets() {
        logger.info("Request received to display all hallticket");
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

    // Get Hallticket by ID
    @GetMapping("/{id}")
    public ResponseEntity<HallticketEntity> getHallticketById(@PathVariable Long id) {
        logger.info("Request received to display hallticket with Student_Id : {}", id);
        Optional<HallticketEntity> hallticket = hallticketService.getHallticketById(id);
        return hallticket.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update Hallticket by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateHallticket(@PathVariable Long id, @RequestBody HallticketEntity hallticketEntity) {
        Optional<HallticketEntity> existingHallticket = hallticketService.getHallticketById(id);
        if (existingHallticket.isPresent()) {
            hallticketEntity.setHallticketID(id);
            hallticketService.updateHallticket(hallticketEntity);
            return new ResponseEntity<>("Hallticket updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Hallticket not found", HttpStatus.NOT_FOUND);
        }
    }

    // Delete Hallticket by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteHallticket(@PathVariable Long id) {
        Optional<HallticketEntity> existingHallticket = hallticketService.getHallticketById(id);
        if (existingHallticket.isPresent()) {
            hallticketService.deleteHallticketById(id);
            return new ResponseEntity<>("Hallticket deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Hallticket not found", HttpStatus.NOT_FOUND);
        }
    }

    // Handle Validation Exceptions
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
