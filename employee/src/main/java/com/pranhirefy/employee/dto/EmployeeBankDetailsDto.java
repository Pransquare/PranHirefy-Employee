package com.pranhirefy.employee.dto;



import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.pranhirefy.employee.entity.EmployeeBasicDetailsEntity;

import jakarta.persistence.Column;
import com.fasterxml.jackson.annotation.JsonFormat;
public class EmployeeBankDetailsDto {

	
    private Long empBankDetailId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date modifiedDate;
    private String bankIfsc;
    private String accountType;
    private String createdBy;
    private String modifiedBy;
    private String deleted;

    private String bankAccountNo;
    private Long employeeBasicDetailId;
    
    public EmployeeBankDetailsDto() {
    	
    }
    
	public EmployeeBankDetailsDto(Long empBankDetailId, Date createdDate, Date modifiedDate, String bankIfsc,
			String accountType, String createdBy, String modifiedBy, String bankAccountNo, Long employeeBasicDetailId) {
		super();
		this.empBankDetailId = empBankDetailId;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.bankIfsc = bankIfsc;
		this.accountType = accountType;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.bankAccountNo = bankAccountNo;
		this.employeeBasicDetailId = employeeBasicDetailId;
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

	public Long getEmployeeBasicDetailId() {
		return employeeBasicDetailId;
	}

	public void setEmployeeBasicDetailId(Long employeeBasicDetailId) {
		this.employeeBasicDetailId = employeeBasicDetailId;
	}

	public void setDeleted(String deleted) {
		 this.deleted = deleted;
		
	}

	

	public String getDeleted() {
		// TODO Auto-generated method stub
		return deleted;
	}
	
    
    
    

    
}

