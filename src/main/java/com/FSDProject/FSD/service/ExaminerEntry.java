package com.FSDProject.FSD.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FSDProject.FSD.entitiy.ExaminerEntity;
import com.FSDProject.FSD.repository.ExaminerRepo;

@Service
public class ExaminerEntry {
    
    @Autowired
    private ExaminerRepo examinerRepo; 

    public ExaminerEntity saveEntry(ExaminerEntity examinerEntity) {  
        return examinerRepo.save(examinerEntity);  
    }

    public List<ExaminerEntity> getAll() {
        return examinerRepo.findAll(); 
    }

    public Optional<ExaminerEntity> getEntryById(Long id) {
        return examinerRepo.findById(id); 
    }

    public String deleteByID(Long id) {  
        Optional<ExaminerEntity> examinerEntity = examinerRepo.findById(id);
        if (examinerEntity.isPresent()) {
            examinerRepo.deleteById(id);
            return "Deleted successfully";
        } else {
            return "Examiner not found with ID: " + id;
        }
    }
    public ExaminerEntity update(ExaminerEntity examinerEntity) {  
        return examinerRepo.save(examinerEntity);  
    }
}

