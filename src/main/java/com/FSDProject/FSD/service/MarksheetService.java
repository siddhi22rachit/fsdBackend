package com.FSDProject.FSD.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FSDProject.FSD.entity.MarksheetEntity;
import com.FSDProject.FSD.repository.MarksheetRepo;

@Service
public class MarksheetService {

    @Autowired
    private MarksheetRepo marksheetRepo;

    // Save Marksheet
    public MarksheetEntity saveMarksheet(MarksheetEntity marksheetEntity) {
        return marksheetRepo.save(marksheetEntity);
    }

    // Get all Marksheets
    public List<MarksheetEntity> getAllMarksheets() {
        return marksheetRepo.findAll();
    }

    // Get Marksheet by ID
    public Optional<MarksheetEntity> getMarksheetById(Long id) {
        return marksheetRepo.findById(id);
    }

    // Delete Marksheet by ID
    public String deleteMarksheetById(Long id) {
        marksheetRepo.deleteById(id);
        return "Marksheet deleted successfully";
    }

    // Update Marksheet
    public MarksheetEntity updateMarksheet(MarksheetEntity marksheetEntity) {
        return marksheetRepo.save(marksheetEntity);
    }
}
