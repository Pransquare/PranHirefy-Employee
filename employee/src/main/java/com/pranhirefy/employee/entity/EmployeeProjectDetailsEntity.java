package com.pranhirefy.employee.entity;
 
import jakarta.persistence.*;
 
 
import java.util.Date;
 
@Entity
@Table(name = "employeeprojectdetails")
 
public class EmployeeProjectDetailsEntity {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_project_id")
    private Long empProjectId;
 
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date createdDate;
 
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_date")
    private Date modifiedDate;
 
    @Column(name = "client_code")
    private String clientCode;
 
    @Column(name = "created_by")
    private String createdBy;
 
    @Column(name = "modified_by")
    private String modifiedBy;
 
    @Column(name = "onshore_status")
    private String onshoreStatus;
 
    @Column(name = "project_code")
    private String projectCode;
 
    @Column(name = "reporting_location")
    private String reportingLocation;
 
    @Column(name = "reporting_manager")
    private String reportingManager;
 
    @Column(name = "status")
    private String status;
    
    
    @Column(name = "is_deleted")
    private String deleted ="No";
 
    @ManyToOne
    @JoinColumn(name = "emp_basic_detail_id")
    private EmployeeBasicDetailsEntity employeeBasicDetails;
    
    public EmployeeProjectDetailsEntity() {
    	
    }
 
	public EmployeeProjectDetailsEntity(Long empProjectId, Date createdDate, Date modifiedDate, String clientCode,
			String createdBy, String modifiedBy, String onshoreStatus, String projectCode, String reportingLocation,
			String reportingManager, String status, String deleted, EmployeeBasicDetailsEntity employeeBasicDetails) {
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
		this.employeeBasicDetails = employeeBasicDetails;
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
 
	public EmployeeBasicDetailsEntity getEmployeeBasicDetails() {
		return employeeBasicDetails;
	}
 
	public void setEmployeeBasicDetails(EmployeeBasicDetailsEntity employeeBasicDetails) {
		this.employeeBasicDetails = employeeBasicDetails;
	}
    
    
    
    
    
}