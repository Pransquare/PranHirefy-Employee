package com.pranhirefy.employee.mapper;

import com.pranhirefy.employee.dto.EmployeeBasicDetailsDto;
import com.pranhirefy.employee.entity.EmployeeBasicDetailsEntity;

public class EmployeeBasicDetailsMapper {

    public static EmployeeBasicDetailsDto toDto(EmployeeBasicDetailsEntity entity) {
        if (entity == null) return null;

        EmployeeBasicDetailsDto dto = new EmployeeBasicDetailsDto();

        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setMiddleName(entity.getMiddleName());
        dto.setLastName(entity.getLastName());
        dto.setGender(entity.getGender());
        dto.setDob(entity.getDob());
        dto.setDesignation(entity.getDesignation());
        dto.setMaritalStatus(entity.getMaritalStatus());
        dto.setMobileNo(entity.getMobileNo());
        dto.setEmailId(entity.getEmailId());
        dto.setBloodGroup(entity.getBloodGroup());
        dto.setPanNo(entity.getPanNo());
        dto.setAdharNo(entity.getAdharNo());
        dto.setUanNo(entity.getUanNo());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setModifiedBy(entity.getModifiedBy());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setStatus(entity.getStatus());
        dto.setWorkflowStatus(entity.getWorkflowStatus());
        dto.setDateOfJoining(entity.getDateOfJoining());
        dto.setLastWorkingDay(entity.getLastWorkingDay());
        dto.setEmployeeCode(entity.getEmployeeCode());
        dto.setFullName(entity.getFullName());
        dto.setEmpGroup(entity.getEmpGroup());
        dto.setEmpSubGroup(entity.getEmpSubGroup());
        dto.setPersonalEmail(entity.getPersonalEmail());
        dto.setNationality(entity.getNationality());
        dto.setWorkType(entity.getWorkType());
        dto.setDocumentNumber(entity.getDocumentNumber());
        dto.setDocumentType(entity.getDocumentType());
        dto.setJiraId(entity.getJiraId());
        dto.setIsProofDeclarationEnabled(entity.getIsProofDeclarationEnabled());
        dto.setIsTaxDeclarationEnabled(entity.getIsTaxDeclarationEnabled());
        dto.setProfilePicPath(entity.getProfilePicPath());
        dto.setProofSubmissionEnabledDate(entity.getProofSubmissionEnabledDate());
        dto.setTaxDeclarationEnabledDate(entity.getTaxDeclarationEnabledDate());
        dto.setTestProfile(entity.getTestProfile());
        dto.setAlternateNumber(entity.getAlternateNumber());
        dto.setEmergencyNumber(entity.getEmergencyNumber());
        dto.setGenericProfile(entity.getGenericProfile());
        dto.setGoalSetup(entity.getGoalSetup());

        return dto;
    }

    public static EmployeeBasicDetailsEntity toEntity(EmployeeBasicDetailsDto dto) {
        if (dto == null) return null;

        EmployeeBasicDetailsEntity entity = new EmployeeBasicDetailsEntity();

        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setMiddleName(dto.getMiddleName());
        entity.setLastName(dto.getLastName());
        entity.setGender(dto.getGender());
        entity.setDob(dto.getDob());
        entity.setDesignation(dto.getDesignation());
        entity.setMaritalStatus(dto.getMaritalStatus());
        entity.setMobileNo(dto.getMobileNo());
        entity.setEmailId(dto.getEmailId());
        entity.setBloodGroup(dto.getBloodGroup());
        entity.setPanNo(dto.getPanNo());
        entity.setAdharNo(dto.getAdharNo());
        entity.setUanNo(dto.getUanNo());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setModifiedBy(dto.getModifiedBy());
        entity.setModifiedDate(dto.getModifiedDate());
        entity.setStatus(dto.getStatus());
        entity.setWorkflowStatus(dto.getWorkflowStatus());
        entity.setDateOfJoining(dto.getDateOfJoining());
        entity.setLastWorkingDay(dto.getLastWorkingDay());
        entity.setEmployeeCode(dto.getEmployeeCode());
        entity.setFullName(dto.getFullName());
        entity.setEmpGroup(dto.getEmpGroup());
        entity.setEmpSubGroup(dto.getEmpSubGroup());
        entity.setPersonalEmail(dto.getPersonalEmail());
        entity.setNationality(dto.getNationality());
        entity.setWorkType(dto.getWorkType());
        entity.setDocumentNumber(dto.getDocumentNumber());
        entity.setDocumentType(dto.getDocumentType());
        entity.setJiraId(dto.getJiraId());
        entity.setIsProofDeclarationEnabled(dto.getIsProofDeclarationEnabled());
        entity.setIsTaxDeclarationEnabled(dto.getIsTaxDeclarationEnabled());
        entity.setProfilePicPath(dto.getProfilePicPath());
        entity.setProofSubmissionEnabledDate(dto.getProofSubmissionEnabledDate());
        entity.setTaxDeclarationEnabledDate(dto.getTaxDeclarationEnabledDate());
        entity.setTestProfile(dto.getTestProfile());
        entity.setAlternateNumber(dto.getAlternateNumber());
        entity.setEmergencyNumber(dto.getEmergencyNumber());
        entity.setGenericProfile(dto.getGenericProfile());
        entity.setGoalSetup(dto.getGoalSetup());

        return entity;
    }
}
