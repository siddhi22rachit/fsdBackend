package com.FSDProject.FSD.entitiy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "examiner_entries")  
public class ExaminerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examinerID;  
    
    private String name;
    private String email;
    private String department;
    private String assignedExams;

    // Getters and Setters
    public Long getExaminerID() {
        return examinerID;
    }

    public void setExaminerID(Long examinerID) {
        this.examinerID = examinerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAssignedExams() {
        return assignedExams;
    }

    public void setAssignedExams(String assignedExams) {
        this.assignedExams = assignedExams;
    }
}
