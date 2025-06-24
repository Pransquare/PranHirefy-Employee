package com.pranhirefy.employee.dto;
 
 
import java.util.Date;
 
public class EmployeeProjectDetailsDto {
    private Long empProjectId;
    private Date createdDate;
    private Date modifiedDate;
    private String clientCode;
    private String createdBy;
    private String modifiedBy;
    private String onshoreStatus;
    private String projectCode;
    private String reportingLocation;
    private String reportingManager;
    private String status;
    private String deleted;
    private Long employeeBasicDetailId;
    
    public EmployeeProjectDetailsDto() {
    	
    }
 
	public EmployeeProjectDetailsDto(Long empProjectId, Date createdDate, Date modifiedDate, String clientCode,
			String createdBy, String modifiedBy, String onshoreStatus, String projectCode, String reportingLocation,
			String reportingManager, String status, String deleted, Long employeeBasicDetailId) {
		super();
		this.empProjectId = empProjectId;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.clientCode = clientCode;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.onshoreStatus = onshoreStatus;
		this.projectCode = projectCode;
		this.reportingLocation = reportingLocation;
		this.reportingManager = reportingManager;
		this.status = status;
		this.deleted = deleted;
		this.employeeBasicDetailId = employeeBasicDetailId;
	}
 
	public Long getEmpProjectId() {
		return empProjectId;
	}
 
	public void setEmpProjectId(Long empProjectId) {
		this.empProjectId = empProjectId;
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
 
	public String getClientCode() {
		return clientCode;
	}
 
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
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
 
	public String getOnshoreStatus() {
		return onshoreStatus;
	}
 
	public void setOnshoreStatus(String onshoreStatus) {
		this.onshoreStatus = onshoreStatus;
	}
 
	public String getProjectCode() {
		return projectCode;
	}
 
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
 
	public String getReportingLocation() {
		return reportingLocation;
	}
 
	public void setReportingLocation(String reportingLocation) {
		this.reportingLocation = reportingLocation;
	}
 
	public String getReportingManager() {
		return reportingManager;
	}
 
	public void setReportingManager(String reportingManager) {
		this.reportingManager = reportingManager;
	}
 
	public String getStatus() {
		return status;
	}
 
	public void setStatus(String status) {
		this.status = status;
	}
 
	public String getDeleted() {
		return deleted;
	}
 
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
 
	public Long getEmployeeBasicDetailId() {
		return employeeBasicDetailId;
	}
 
	public void setEmployeeBasicDetailId(Long employeeBasicDetailId) {
		this.employeeBasicDetailId = employeeBasicDetailId;
	}
    
    
    
    
}