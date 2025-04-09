package com.FSDProject.FSD.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FSDProject.FSD.entity.EnrollmentEntity;
import com.FSDProject.FSD.repository.EnrollmentRepo;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepo enrollmentRepo;

    // Save Enrollment
    public EnrollmentEntity saveEnrollment(EnrollmentEntity enrollmentEntity) {
        return enrollmentRepo.save(enrollmentEntity);
    }

    // Get all Enrollments
    public List<EnrollmentEntity> getAllEnrollment() {
        return enrollmentRepo.findAll();
    }

    // Get Enrollment by ID
    public Optional<EnrollmentEntity> getEnrollmentById(Long id) {
        return enrollmentRepo.findById(id);
    }

    // Delete Enrollment by ID
    public String deleteEnrollmentById(Long id) {
        enrollmentRepo.deleteById(id);
        return "Enrollment deleted successfully";
    }

    // Update Enrollment
    public EnrollmentEntity updateEnrollment(EnrollmentEntity enrollmentEntity) {
        return enrollmentRepo.save(enrollmentEntity);
    }
}
