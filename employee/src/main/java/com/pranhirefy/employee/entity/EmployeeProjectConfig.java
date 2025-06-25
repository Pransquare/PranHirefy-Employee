package com.pranhirefy.employee.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee_project_config")
public class EmployeeProjectConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeProjectConfigId;

    @ManyToOne
    @JoinColumn( name = "emp_basic_detail_id", referencedColumnName = "employee_basic_detail_id", nullable = false )
    private EmployeeBasicDetailsEntity employee;

    private String projectCode;

    private String createdBy;

    private LocalDateTime createdDate;

    private String modifiedBy;

    private LocalDateTime modifiedDate;

    private String status;

    private LocalDateTime allocationEndDate;

    private LocalDateTime allocationStartDate;

    private String isDeleted = "No";

	public Long getEmployeeProjectConfigId() {
		return employeeProjectConfigId;
	}

	public void setEmployeeProjectConfigId(Long employeeProjectConfigId) {
		this.employeeProjectConfigId = employeeProjectConfigId;
	}

	public EmployeeBasicDetailsEntity getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeBasicDetailsEntity employee) {
		this.employee = employee;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getAllocationEndDate() {
		return allocationEndDate;
	}

	public void setAllocationEndDate(LocalDateTime allocationEndDate) {
		this.allocationEndDate = allocationEndDate;
	}

	public LocalDateTime getAllocationStartDate() {
		return allocationStartDate;
	}

	public void setAllocationStartDate(LocalDateTime allocationStartDate) {
		this.allocationStartDate = allocationStartDate;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

    // Getters and Setters
    
}
