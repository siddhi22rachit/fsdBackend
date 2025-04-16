package com.FSDProject.FSD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FSDProject.FSD.entity.HallticketEntity;

@Repository
public interface HallticketRepo extends JpaRepository<HallticketEntity, Long> {
}
