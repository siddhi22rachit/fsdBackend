package com.FSDProject.FSD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FSDProject.FSD.entity.TeacherEntity;

@Repository
public interface TeacherRepo extends JpaRepository<TeacherEntity, Long> {
}