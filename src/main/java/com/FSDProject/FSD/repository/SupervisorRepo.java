package com.FSDProject.FSD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FSDProject.FSD.entity.SupervisorEntity;

@Repository
public interface SupervisorRepo extends JpaRepository<SupervisorEntity, Long> {
}