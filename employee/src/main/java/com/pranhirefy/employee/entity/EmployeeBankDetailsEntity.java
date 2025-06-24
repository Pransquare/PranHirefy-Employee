package com.pranhirefy.employee.entity;



import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee_bank_details")

public class EmployeeBankDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_bank_detail_id")
    private Long empBankDetailId;

    @Column(name = "createddate")
    private Date createdDate;

    @Column(name = "modifieddate")
    private Date modifiedDate;

    @Column(name = "bank_ifsc", length = 20)
    private String bankIfsc;
    
    @Column(name = "is_deleted")
    private String deleted ="No";

    @Column(name = "account_type", length = 50)
    private String accountType;

    @Column(name = "createdby", length = 50)
    private String createdBy;

    @Column(name = "modifiedby", length = 50)
    private String modifiedBy;


    
    private String bankAccountNo;


    
    @ManyToOne
    @JoinColumn(name = "employee_basic_detail_id", nullable = false)
    private EmployeeBasicDetailsEntity employeeBasicDetails;

    public EmployeeBankDetailsEntity() {
    	
    }
public EmployeeBankDetailsEntity(Long empBankDetailId, Date createdDate, Date modifiedDate, String bankIfsc,
		String deleted, String accountType, String createdBy, String modifiedBy, String bankAccountNo,
		EmployeeBasicDetailsEntity employeeBasicDetails) {
	super();
	this.empBankDetailId = empBankDetailId;
	this.createdDate = createdDate;
	this.modifiedDate = modifiedDate;
	this.bankIfsc = bankIfsc;
	this.deleted = deleted;
	this.accountType = accountType;
	this.createdBy = createdBy;
	this.modifiedBy = modifiedBy;
	this.bankAccountNo = bankAccountNo;
	this.employeeBasicDetails = employeeBasicDetails;
}


public Long getEmpBankDetailId() {
	return empBankDetailId;
}
public void setEmpBankDetailId(Long empBankDetailId) {
	this.empBankDetailId = empBankDetailId;
}
public Date getCreatedDate() {
	return createdDate;
}
public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
}
public Date getModifiedDate() {
	return modifiedDate;
}
public void setModifiedDate(Date modifiedDate) {
	this.modifiedDate = modifiedDate;
}
public String getBankIfsc() {
	return bankIfsc;
}
public void setBankIfsc(String bankIfsc) {
	this.bankIfsc = bankIfsc;
}
public String getDeleted() {
	return deleted;
}
public void setDeleted(String deleted) {
	this.deleted = deleted;
}
public String getAccountType() {
	return accountType;
}
public void setAccountType(String accountType) {
	this.accountType = accountType;
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
public String getBankAccountNo() {
	return bankAccountNo;
}
public void setBankAccountNo(String bankAccountNo) {
	this.bankAccountNo = bankAccountNo;
}
public EmployeeBasicDetailsEntity getEmployeeBasicDetails() {
	return employeeBasicDetails;
}
public void setEmployeeBasicDetails(EmployeeBasicDetailsEntity employeeBasicDetails) {
	this.employeeBasicDetails = employeeBasicDetails;
}
public void softDelete() {
    this.deleted = "Yes";
}
    
}
