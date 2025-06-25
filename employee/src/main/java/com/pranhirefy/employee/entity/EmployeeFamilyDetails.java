
package com.pranhirefy.employee.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "employee_family_details")
public class EmployeeFamilyDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empFamilyDetailId;

    private String name;
    private String relation;
    private String isInsuranceRequired;
    private LocalDateTime dob;
    private String createdBy;
    private String modifiedBy;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Column(name = "is_deleted", nullable = false)
    private String isDeleted = "No";

    @ManyToOne
    @JoinColumn(name = "emp_basic_detail_id", referencedColumnName = "employee_basic_detail_id")
    private EmployeeBasicDetailsEntity empBasicDetail;

    // Getters and Setters

    public Long getEmpFamilyDetailId() {
        return empFamilyDetailId;
    }

    public void setEmpFamilyDetailId(Long empFamilyDetailId) {
        this.empFamilyDetailId = empFamilyDetailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getIsInsuranceRequired() {
        return isInsuranceRequired;
    }

    public void setIsInsuranceRequired(String isInsuranceRequired) {
        this.isInsuranceRequired = isInsuranceRequired;
    }

    public LocalDateTime getDob() {
        return dob;
    }

    public void setDob(LocalDateTime dob) {
        this.dob = dob;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public EmployeeBasicDetailsEntity getEmpBasicDetail() {
        return empBasicDetail;
    }

    public void setEmpBasicDetail(EmployeeBasicDetailsEntity empBasicDetail) {
        this.empBasicDetail = empBasicDetail;
    }
}

