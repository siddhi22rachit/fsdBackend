package com.FSDProject.FSD.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "hallticket_entries")  
public class HallticketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hallticketID;  
    
    private String name;
    private String examName;
    private String examDate;
    private String academicYear;

    // Getters and Setters
    public Long getHallticketID() {
        return hallticketID;
    }

    public void setHallticketID(Long hallticketID) {
        this.hallticketID = hallticketID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }
}
