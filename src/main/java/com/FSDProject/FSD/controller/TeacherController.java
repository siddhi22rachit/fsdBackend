package com.FSDProject.FSD.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.FSDProject.FSD.entity.TeacherEntity;
import com.FSDProject.FSD.service.TeacherService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/create")
    public ResponseEntity<String> createTeacher(@RequestBody TeacherEntity teacherEntity) {
        logger.info("Request received to create a teacher with name: {}", teacherEntity.getName());
        try {
            teacherService.saveTeacher(teacherEntity);
            logger.info("Teacher created successfully with ID: {}", teacherEntity.getTeacherID());
            return new ResponseEntity<>("Teacher created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating teacher: {}", e.getMessage());
            return new ResponseEntity<>("Error creating teacher: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<TeacherEntity>> getAllTeachers() {
        logger.info("Request received to get all teachers");
        try {
            List<TeacherEntity> teachers = teacherService.getAllTeachers();
            if (teachers.isEmpty()) {
                logger.warn("No teachers found");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            logger.info("Returning list of {} teachers", teachers.size());
            return new ResponseEntity<>(teachers, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching teachers: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable Long id) {
        logger.info("Request received to delete teacher with ID: {}", id);
        try {
            Optional<TeacherEntity> teacherData = teacherService.getTeacherById(id);
            if (teacherData.isPresent()) {
                teacherService.deleteTeacherById(id);
                logger.info("Teacher deleted successfully with ID: {}", id);
                return new ResponseEntity<>("Teacher deleted successfully", HttpStatus.OK);
            } else {
                logger.warn("Teacher not found with ID: {}", id);
                return new ResponseEntity<>("Teacher not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error deleting teacher with ID {}: {}", id, e.getMessage());
            return new ResponseEntity<>("Error deleting teacher: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
