package com.FSDProject.FSD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FSDProject.FSD.entitiy.ExaminerEntity;
import com.FSDProject.FSD.service.ExaminerEntry;

@Repository
@SuppressWarnings("unused")
public interface ExaminerRepo extends JpaRepository<ExaminerEntity, Long> {

}
