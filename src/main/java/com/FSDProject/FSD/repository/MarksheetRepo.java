package com.FSDProject.FSD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.FSDProject.FSD.entitiy.MarksheetEntity;

@Repository
public interface MarksheetRepo extends JpaRepository<MarksheetEntity, Long> {
}
