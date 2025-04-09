package com.FSDProject.FSD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FSDProject.FSD.entity.EnrollmentEntity;

@Repository
public interface EnrollmentRepo extends JpaRepository<EnrollmentEntity, Long> {
}