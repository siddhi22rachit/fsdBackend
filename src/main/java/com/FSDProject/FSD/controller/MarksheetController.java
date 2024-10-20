package com.FSDProject.FSD.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.FSDProject.FSD.entitiy.MarksheetEntity;
import com.FSDProject.FSD.service.MarksheetService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/marksheet")
public class MarksheetController {
    private static final Logger logger = LoggerFactory.getLogger(ExaminerController.class);
    @Autowired
    private MarksheetService marksheetService;

    // Create a new Marksheet
    @PostMapping("/create")
    public ResponseEntity<String> createMarksheet(@RequestBody MarksheetEntity marksheetEntity) {
        logger.info("Request received to create an marksheet with name: {}", marksheetEntity.getStudentName());
        marksheetService.saveMarksheet(marksheetEntity);
        return new ResponseEntity<>("Marksheet created successfully", HttpStatus.CREATED);
    }

    // Get all Marksheets
    @GetMapping("/all")
    public ResponseEntity<List<MarksheetEntity>> getAllMarksheets() {
        logger.info("Request received to display all marksheet");
        try {
            List<MarksheetEntity> marksheets = marksheetService.getAllMarksheets();
        if (marksheets.isEmpty()) {
            logger.warn("No marksheets found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("Returning list of {} marksheets", marksheets.size());
        return new ResponseEntity<>(marksheets, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching marksheets: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get Marksheet by ID
    @GetMapping("/{id}")
    public ResponseEntity<MarksheetEntity> getMarksheetById(@PathVariable Long id) {
        logger.info("Request received to display marksheet with Student_Id : {}", id);
        Optional<MarksheetEntity> marksheet = marksheetService.getMarksheetById(id);
        return marksheet.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update Marksheet by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateMarksheet(@PathVariable Long id, @RequestBody MarksheetEntity marksheetEntity) {
        Optional<MarksheetEntity> existingMarksheet = marksheetService.getMarksheetById(id);
        if (existingMarksheet.isPresent()) {
            marksheetEntity.setMarksheetID(id);
            marksheetService.updateMarksheet(marksheetEntity);
            return new ResponseEntity<>("Marksheet updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Marksheet not found", HttpStatus.NOT_FOUND);
        }
    }

    // Delete Marksheet by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMarksheet(@PathVariable Long id) {
        Optional<MarksheetEntity> existingMarksheet = marksheetService.getMarksheetById(id);
        if (existingMarksheet.isPresent()) {
            marksheetService.deleteMarksheetById(id);
            return new ResponseEntity<>("Marksheet deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Marksheet not found", HttpStatus.NOT_FOUND);
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
