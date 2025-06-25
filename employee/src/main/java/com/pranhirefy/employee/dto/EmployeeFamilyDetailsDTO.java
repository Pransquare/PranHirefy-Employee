//package com.pranhirefy.employee.dto;
//
//import java.time.LocalDateTime;
//
//public class EmployeeFamilyDetailsDTO {
//    private Long empFamilyDetailId;
//    private String name;
//    private String relation;
//    private String isInsuranceRequired;
//    private LocalDateTime dob;
//    private String createdBy;
//    private String modifiedBy;
//    private LocalDateTime createdDate;
//    private LocalDateTime modifiedDate;
//    private Long empBasicDetailId;
//	public Long getEmpFamilyDetailId() {
//		return empFamilyDetailId;
//	}
//	public void setEmpFamilyDetailId(Long empFamilyDetailId) {
//		this.empFamilyDetailId = empFamilyDetailId;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public String getRelation() {
//		return relation;
//	}
//	public void setRelation(String relation) {
//		this.relation = relation;
//	}
//	public String getIsInsuranceRequired() {
//		return isInsuranceRequired;
//	}
//	public void setIsInsuranceRequired(String isInsuranceRequired) {
//		this.isInsuranceRequired = isInsuranceRequired;
//	}
//	public LocalDateTime getDob() {
//		return dob;
//	}
//	public void setDob(LocalDateTime dob) {
//		this.dob = dob;
//	}
//	public String getCreatedBy() {
//		return createdBy;
//	}
//	public void setCreatedBy(String createdBy) {
//		this.createdBy = createdBy;
//	}
//	public String getModifiedBy() {
//		return modifiedBy;
//	}
//	public void setModifiedBy(String modifiedBy) {
//		this.modifiedBy = modifiedBy;
//	}
//	public LocalDateTime getCreatedDate() {
//		return createdDate;
//	}
//	public void setCreatedDate(LocalDateTime createdDate) {
//		this.createdDate = createdDate;
//	}
//	public LocalDateTime getModifiedDate() {
//		return modifiedDate;
//	}
//	public void setModifiedDate(LocalDateTime modifiedDate) {
//		this.modifiedDate = modifiedDate;
//	}
//	public Long getEmpBasicDetailId() {
//		return empBasicDetailId;
//	}
//	public void setEmpBasicDetailId(Long empBasicDetailId) {
//		this.empBasicDetailId = empBasicDetailId;
//	}
//
//    // Getters and Setters
//    
//}





package com.pranhirefy.employee.dto;

import java.time.LocalDateTime;

public class EmployeeFamilyDetailsDTO {

    private Long empFamilyDetailId;
    private String name;
    private String relation;
    private String isInsuranceRequired;
    private LocalDateTime dob;
    private String createdBy;
    private String modifiedBy;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Long empBasicDetailId;

    private String isDeleted; // ✅ Soft delete flag (Y/N)

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

    public Long getEmpBasicDetailId() {
        return empBasicDetailId;
    }

    public void setEmpBasicDetailId(Long empBasicDetailId) {
        this.empBasicDetailId = empBasicDetailId;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }
}

