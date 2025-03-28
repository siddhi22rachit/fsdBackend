package com.FSDProject.FSD.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FSDProject.FSD.entitiy.ExaminerEntity;
import com.FSDProject.FSD.service.ExaminerEntry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin
@RestController
@RequestMapping("/examiner")
public class ExaminerController {

    private static final Logger logger = LoggerFactory.getLogger(ExaminerController.class);
     @Autowired
    private ExaminerEntry examinerEntry;

    

    @PostMapping("/create")
    public ResponseEntity<String> createExaminer(@RequestBody ExaminerEntity examinerEntity) {
        logger.info("Request received to create an examiner with name: {}", examinerEntity.getName());
        try {
            examinerEntry.saveEntry(examinerEntity);
            logger.info("Examiner created successfully with ID: {}", examinerEntity.getExaminerID());
            return new ResponseEntity<>("Examiner created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating examiner: {}", e.getMessage());
            return new ResponseEntity<>("Error creating examiner: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ExaminerEntity>> getAllExaminers() {
        logger.info("Request received to get all examiners");
        try {
            List<ExaminerEntity> examiners = examinerEntry.getAll();
            if (examiners.isEmpty()) {
                logger.warn("No examiners found");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            logger.info("Returning list of {} examiners", examiners.size());
            return new ResponseEntity<>(examiners, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching examiners: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<ExaminerEntity> getExaminerById(@PathVariable Long id) {
        logger.info("Request received to get examiner with ID: {}", id);
        Optional<ExaminerEntity> examinerData = examinerEntry.getEntryById(id);

        if (examinerData.isPresent()) {
            logger.info("Examiner found with ID: {}", id);
            return new ResponseEntity<>(examinerData.get(), HttpStatus.OK);
        } else {
            logger.warn("Examiner not found with ID: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateExaminer(@PathVariable Long id, @RequestBody ExaminerEntity examinerEntity) {
        logger.info("Request received to update examiner with ID: {}", id);
        Optional<ExaminerEntity> existingExaminer = examinerEntry.getEntryById(id);

        if (existingExaminer.isPresent()) {
            examinerEntity.setExaminerID(id);
            examinerEntry.update(examinerEntity);
            logger.info("Examiner updated successfully with ID: {}", id);
            return new ResponseEntity<>("Examiner updated successfully", HttpStatus.OK);
        } else {
            logger.warn("Examiner not found with ID: {}", id);
            return new ResponseEntity<>("Examiner not found", HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteExaminer(@PathVariable Long id) {
        logger.info("Request received to delete examiner with ID: {}", id);
        try {
            Optional<ExaminerEntity> examinerData = examinerEntry.getEntryById(id);
            if (examinerData.isPresent()) {
                examinerEntry.deleteByID(id);
                logger.info("Examiner deleted successfully with ID: {}", id);
                return new ResponseEntity<>("Examiner deleted successfully", HttpStatus.OK);
            } else {
                logger.warn("Examiner not found with ID: {}", id);
                return new ResponseEntity<>("Examiner not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error deleting examiner with ID {}: {}", id, e.getMessage());
            return new ResponseEntity<>("Error deleting examiner: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}



