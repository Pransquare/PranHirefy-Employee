//package com.pranhirefy.employee.mapper;
//
//import com.pranhirefy.employee.dto.EmployeeFamilyDetailsDTO;
//import com.pranhirefy.employee.entity.EmployeeFamilyDetails;
//
//public class EmployeeFamilyDetailsMapper {
//    public static EmployeeFamilyDetailsDTO toDTO(EmployeeFamilyDetails entity) {
//        EmployeeFamilyDetailsDTO dto = new EmployeeFamilyDetailsDTO();
//        dto.setEmpFamilyDetailId(entity.getEmpFamilyDetailId());
//        dto.setName(entity.getName());
//        dto.setRelation(entity.getRelation());
//        dto.setIsInsuranceRequired(entity.getIsInsuranceRequired());
//        dto.setDob(entity.getDob());
//        dto.setCreatedBy(entity.getCreatedBy());
//        dto.setModifiedBy(entity.getModifiedBy());
//        dto.setCreatedDate(entity.getCreatedDate());
//        dto.setModifiedDate(entity.getModifiedDate());
//        dto.setEmpBasicDetailId(entity.getEmpBasicDetail().getId());
//        return dto;
//    }
//
//    public static EmployeeFamilyDetails toEntity(EmployeeFamilyDetailsDTO dto) {
//        EmployeeFamilyDetails entity = new EmployeeFamilyDetails();
//        entity.setEmpFamilyDetailId(dto.getEmpFamilyDetailId());
//        entity.setName(dto.getName());
//        entity.setRelation(dto.getRelation());
//        entity.setIsInsuranceRequired(dto.getIsInsuranceRequired());
//        entity.setDob(dto.getDob());
//        entity.setCreatedBy(dto.getCreatedBy());
//        entity.setModifiedBy(dto.getModifiedBy());
//        entity.setCreatedDate(dto.getCreatedDate());
//        entity.setModifiedDate(dto.getModifiedDate());
//        return entity;
//    }
//}







package com.pranhirefy.employee.mapper;

import com.pranhirefy.employee.dto.EmployeeFamilyDetailsDTO;
import com.pranhirefy.employee.entity.EmployeeFamilyDetails;

public class EmployeeFamilyDetailsMapper {

    public static EmployeeFamilyDetailsDTO toDTO(EmployeeFamilyDetails entity) {
        EmployeeFamilyDetailsDTO dto = new EmployeeFamilyDetailsDTO();
        dto.setEmpFamilyDetailId(entity.getEmpFamilyDetailId());
        dto.setName(entity.getName());
        dto.setRelation(entity.getRelation());
        dto.setIsInsuranceRequired(entity.getIsInsuranceRequired());
        dto.setDob(entity.getDob());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setModifiedBy(entity.getModifiedBy());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setModifiedDate(entity.getModifiedDate());

        // ✅ Set empBasicDetailId safely
        if (entity.getEmpBasicDetail() != null) {
            dto.setEmpBasicDetailId(entity.getEmpBasicDetail().getId());
        }

        // ✅ Include soft delete field
        dto.setIsDeleted(entity.getIsDeleted());
        return dto;
    }

    public static EmployeeFamilyDetails toEntity(EmployeeFamilyDetailsDTO dto) {
        EmployeeFamilyDetails entity = new EmployeeFamilyDetails();
        entity.setEmpFamilyDetailId(dto.getEmpFamilyDetailId());
        entity.setName(dto.getName());
        entity.setRelation(dto.getRelation());
        entity.setIsInsuranceRequired(dto.getIsInsuranceRequired());
        entity.setDob(dto.getDob());
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setModifiedBy(dto.getModifiedBy());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setModifiedDate(dto.getModifiedDate());

        // ✅ Include soft delete field
        entity.setIsDeleted(dto.getIsDeleted() != null ? dto.getIsDeleted() : "N");

        return entity;
    }
}

