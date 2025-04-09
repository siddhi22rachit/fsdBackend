package com.FSDProject.FSD.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "supervisor_entries")  
public class SupervisorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supervisorID;  
    
    private String name;
    private String email;
    private String department;
    private String assignedHall;

    // Getters and Setters
    public Long getSupervisorID() {
        return supervisorID;
    }

    public void setSupervisorID(Long supervisorID) {
        this.supervisorID = supervisorID;
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

    public String getAssignedHall() {
        return assignedHall;
    }

    public void setAssignedHall(String assignedHall) {
        this.assignedHall = assignedHall;
    }
}
