package com.FSDProject.FSD.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FSDProject.FSD.entity.TeacherEntity;
import com.FSDProject.FSD.repository.TeacherRepo;

@Service
public class TeacherService {
    
    @Autowired
    private TeacherRepo teacherRepo; 

    public TeacherEntity saveTeacher(TeacherEntity teacherEntity) {  
        return teacherRepo.save(teacherEntity);  
    }

    public List<TeacherEntity> getAllTeachers() {
        return teacherRepo.findAll(); 
    }

    public Optional<TeacherEntity> getTeacherById(Long id) {
        return teacherRepo.findById(id); 
    }

    public String deleteTeacherById(Long id) {  
        Optional<TeacherEntity> teacherEntity = teacherRepo.findById(id);
        if (teacherEntity.isPresent()) {
            teacherRepo.deleteById(id);
            return "Deleted successfully";
        } else {
            return "Teacher not found with ID: " + id;
        }
    }
}
