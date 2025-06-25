package com.pranhirefy.employee.mapper;

import com.pranhirefy.employee.dto.EmployeeProjectConfigDTO;
import com.pranhirefy.employee.entity.EmployeeProjectConfig;
import com.pranhirefy.employee.entity.EmployeeBasicDetailsEntity;

public class EmployeeProjectConfigMapper {

    public static EmployeeProjectConfigDTO toDTO(EmployeeProjectConfig entity) {
        EmployeeProjectConfigDTO dto = new EmployeeProjectConfigDTO();
        dto.setEmployeeProjectConfigId(entity.getEmployeeProjectConfigId());
        dto.setEmployeeId(entity.getEmployee().getId());
        dto.setProjectCode(entity.getProjectCode());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setModifiedBy(entity.getModifiedBy());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setStatus(entity.getStatus());
        dto.setAllocationEndDate(entity.getAllocationEndDate());
        dto.setAllocationStartDate(entity.getAllocationStartDate());
        dto.setIsDeleted(entity.getIsDeleted());
        return dto;
    }

    public static EmployeeProjectConfig toEntity(EmployeeProjectConfigDTO dto, EmployeeBasicDetailsEntity employee) {
        EmployeeProjectConfig entity = new EmployeeProjectConfig();
        entity.setEmployeeProjectConfigId(dto.getEmployeeProjectConfigId());
        entity.setEmployee(employee);
        entity.setProjectCode(dto.getProjectCode());
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setModifiedBy(dto.getModifiedBy());
        entity.setModifiedDate(dto.getModifiedDate());
        entity.setStatus(dto.getStatus());
        entity.setAllocationEndDate(dto.getAllocationEndDate());
        entity.setAllocationStartDate(dto.getAllocationStartDate());
        entity.setIsDeleted(dto.getIsDeleted() != null ? dto.getIsDeleted() : "No");
        return entity;
    }
}
