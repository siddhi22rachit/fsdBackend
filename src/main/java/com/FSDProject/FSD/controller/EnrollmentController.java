package com.FSDProject.FSD.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.FSDProject.FSD.entity.EnrollmentEntity;
import com.FSDProject.FSD.service.EnrollmentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin
@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {

    private static final Logger logger = LoggerFactory.getLogger(EnrollmentController.class);

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/create")
    public ResponseEntity<String> createEnrollment(@RequestBody EnrollmentEntity enrollmentEntity) {
        logger.info("Request received to create a enrollment with name: {}", enrollmentEntity.getName());
        try {
          enrollmentService.saveEnrollment(enrollmentEntity);
            logger.info("Enrollment created successfully with ID: {}",enrollmentEntity.getEnrollmentID());
            return new ResponseEntity<>("Enrollment created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating Enrollment: {}", e.getMessage());
            return new ResponseEntity<>("Error creating Enrollment: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<EnrollmentEntity>> getAllEnrollment() {
        logger.info("Request received to get all Enrollment");
        try {
            List<EnrollmentEntity> enrollment = enrollmentService.getAllEnrollment();
            if (enrollment.isEmpty()) {
                logger.warn("No Enrollment found");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            logger.info("Returning list of {} Enrollment", enrollment.size());
            return new ResponseEntity<>(enrollment, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching Enrollment: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEnrollment(@PathVariable Long id) {
        logger.info("Request received to delete Enrollment with ID: {}", id);
        try {
            Optional<EnrollmentEntity> enrollmentData = enrollmentService.getEnrollmentById(id);
            if (enrollmentData.isPresent()) {
              enrollmentService.deleteEnrollmentById(id);
                logger.info("Enrollment deleted successfully with ID: {}", id);
                return new ResponseEntity<>("Enrollment deleted successfully", HttpStatus.OK);
            } else {
                logger.warn("Enrollment not found with ID: {}", id);
                return new ResponseEntity<>("Enrollment not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error deleting Enrollment with ID {}: {}", id, e.getMessage());
            return new ResponseEntity<>("Error deleting Enrollment: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
