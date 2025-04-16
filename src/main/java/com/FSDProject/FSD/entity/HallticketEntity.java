package com.FSDProject.FSD.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "halltickets")
public class HallticketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hallticketID;

    private String studentId;

    private String studentName;

    private String examId;

    private String examName;

    private LocalDate examDate;

    private String academicYear;

    // Getters and Setters
    public Long getHallticketID() {
        return hallticketID;
    }

    public void setHallticketID(Long hallticketID) {
        this.hallticketID = hallticketID;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }

    

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }
}
