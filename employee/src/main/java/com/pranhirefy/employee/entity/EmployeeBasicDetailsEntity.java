package com.pranhirefy.employee.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employee_basic_details")
public class EmployeeBasicDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_BASIC_DETAIL_ID")
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "DOB")
    private Date dob;

    @Column(name = "DESIGNATION")
    private String designation;

    @Column(name = "MARITAL_STATUS")
    private String maritalStatus;

    @Column(name = "MOBILE_NO")
    private String mobileNo;

    @Column(name = "EMAIL_ID")
    private String emailId;

    @Column(name = "BLOOD_GROUP")
    private String bloodGroup;

    @Column(name = "PAN_NO")
    private String panNo;

    @Column(name = "ADHAR_NO")
    private String adharNo;

    @Column(name = "UAN_NO")
    private String uanNo;

    @Column(name = "CREATEDDATE")
    private Date createdDate;

    @Column(name = "CREATEDBY")
    private String createdBy;

    @Column(name = "MODIFIEDBY")
    private String modifiedBy;

    @Column(name = "MODIFIEDDATE")
    private Date modifiedDate;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "WORKFLOW_STATUS")
    private String workflowStatus;

    @Column(name = "DATE_OF_JOINING")
    private Date dateOfJoining;

    @Column(name = "LAST_WORKING_DAY")
    private Date lastWorkingDay;

    @Column(name = "EMPLOYEE_CODE", unique = true, nullable = false)
    private String employeeCode;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "EMP_GROUP")
    private String empGroup;

    @Column(name = "EMP_SUB_GROUP")
    private String empSubGroup;

    @Column(name = "PERSONAL_EMAIL")
    private String personalEmail;

    @Column(name = "NATIONALITY")
    private String nationality;

    @Column(name = "WORK_TYPE")
    private String workType;

    @Column(name = "document_number")
    private String documentNumber;

    @Column(name = "document_type")
    private String documentType;

    @Column(name = "jira_id")
    private String jiraId;

    @Column(name = "is_proofdeclaration_enabled")
    private Integer isProofDeclarationEnabled;

    @Column(name = "is_taxdeclaration_enabled")
    private Integer isTaxDeclarationEnabled;

    @Column(name = "profile_pic_path")
    private String profilePicPath;

    @Column(name = "proof_submission_enabled_date")
    private Date proofSubmissionEnabledDate;

    @Column(name = "tax_declaration_enabled_date")
    private Date taxDeclarationEnabledDate;

    @Column(name = "test_profile")
    private Boolean testProfile;

    @Column(name = "alternate_number")
    private String alternateNumber;

    @Column(name = "emergency_number")
    private String emergencyNumber;

    @Column(name = "generic_profile")
    private Boolean genericProfile;

    @Column(name = "goal_setup")
    private String goalSetup;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getAdharNo() {
		return adharNo;
	}

	public void setAdharNo(String adharNo) {
		this.adharNo = adharNo;
	}

	public String getUanNo() {
		return uanNo;
	}

	public void setUanNo(String uanNo) {
		this.uanNo = uanNo;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWorkflowStatus() {
		return workflowStatus;
	}

	public void setWorkflowStatus(String workflowStatus) {
		this.workflowStatus = workflowStatus;
	}

	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public Date getLastWorkingDay() {
		return lastWorkingDay;
	}

	public void setLastWorkingDay(Date lastWorkingDay) {
		this.lastWorkingDay = lastWorkingDay;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmpGroup() {
		return empGroup;
	}

	public void setEmpGroup(String empGroup) {
		this.empGroup = empGroup;
	}

	public String getEmpSubGroup() {
		return empSubGroup;
	}

	public void setEmpSubGroup(String empSubGroup) {
		this.empSubGroup = empSubGroup;
	}

	public String getPersonalEmail() {
		return personalEmail;
	}

	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getJiraId() {
		return jiraId;
	}

	public void setJiraId(String jiraId) {
		this.jiraId = jiraId;
	}

	public Integer getIsProofDeclarationEnabled() {
		return isProofDeclarationEnabled;
	}

	public void setIsProofDeclarationEnabled(Integer isProofDeclarationEnabled) {
		this.isProofDeclarationEnabled = isProofDeclarationEnabled;
	}

	public Integer getIsTaxDeclarationEnabled() {
		return isTaxDeclarationEnabled;
	}

	public void setIsTaxDeclarationEnabled(Integer isTaxDeclarationEnabled) {
		this.isTaxDeclarationEnabled = isTaxDeclarationEnabled;
	}

	public String getProfilePicPath() {
		return profilePicPath;
	}

	public void setProfilePicPath(String profilePicPath) {
		this.profilePicPath = profilePicPath;
	}

	public Date getProofSubmissionEnabledDate() {
		return proofSubmissionEnabledDate;
	}

	public void setProofSubmissionEnabledDate(Date proofSubmissionEnabledDate) {
		this.proofSubmissionEnabledDate = proofSubmissionEnabledDate;
	}

	public Date getTaxDeclarationEnabledDate() {
		return taxDeclarationEnabledDate;
	}

	public void setTaxDeclarationEnabledDate(Date taxDeclarationEnabledDate) {
		this.taxDeclarationEnabledDate = taxDeclarationEnabledDate;
	}

	public Boolean getTestProfile() {
		return testProfile;
	}

	public void setTestProfile(Boolean testProfile) {
		this.testProfile = testProfile;
	}

	public String getAlternateNumber() {
		return alternateNumber;
	}

	public void setAlternateNumber(String alternateNumber) {
		this.alternateNumber = alternateNumber;
	}

	public String getEmergencyNumber() {
		return emergencyNumber;
	}

	public void setEmergencyNumber(String emergencyNumber) {
		this.emergencyNumber = emergencyNumber;
	}

	public Boolean getGenericProfile() {
		return genericProfile;
	}

	public void setGenericProfile(Boolean genericProfile) {
		this.genericProfile = genericProfile;
	}

	public String getGoalSetup() {
		return goalSetup;
	}

	public void setGoalSetup(String goalSetup) {
		this.goalSetup = goalSetup;
	}

  
}
