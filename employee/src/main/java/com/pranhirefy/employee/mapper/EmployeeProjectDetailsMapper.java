//package com.pranhirefy.employee.mapper;
// 
//import com.pranhirefy.employee.dto.EmployeeProjectDetailsDto;
//import com.pranhirefy.employee.entity.EmployeeBasicDetailsEntity;
//import com.pranhirefy.employee.entity.EmployeeProjectDetailsEntity;
//import org.springframework.stereotype.Component;
// 
//@Component
//public class EmployeeProjectDetailsMapper {
// 
//    public EmployeeProjectDetailsDto toDto(EmployeeProjectDetailsEntity entity) {
//        if (entity == null) {
//            return null;
//        }
// 
//        EmployeeProjectDetailsDto dto = new EmployeeProjectDetailsDto();
//        dto.setEmpProjectId(entity.getEmpProjectId());
//        dto.setCreatedDate(entity.getCreatedDate());
//        dto.setModifiedDate(entity.getModifiedDate());
//        dto.setClientCode(entity.getClientCode());
//        dto.setCreatedBy(entity.getCreatedBy());
//        dto.setModifiedBy(entity.getModifiedBy());
//        dto.setOnshoreStatus(entity.getOnshoreStatus());
//        dto.setProjectCode(entity.getProjectCode());
//        dto.setReportingLocation(entity.getReportingLocation());
//        dto.setReportingManager(entity.getReportingManager());
//        dto.setStatus(entity.getStatus());
//        dto.setDeleted(entity.getDeleted());
//        if (entity.getEmployeeBasicDetails() != null) {
//            dto.setEmployeeBasicDetailId(entity.getEmployeeBasicDetails().getId());
//        }
// 
//        return dto;
//    }
// 
//    public EmployeeProjectDetailsEntity toEntity(EmployeeProjectDetailsDto dto) {
//        if (dto == null || empBasicEntity == null) {
//            return null;
//        }
// 
//        EmployeeProjectDetailsEntity entity = new EmployeeProjectDetailsEntity(null, null, null, null, null, null, null, null, null, null, null, null, empBasicEntity);
//        entity.setEmpProjectId(dto.getEmpProjectId());
//        entity.setCreatedDate(dto.getCreatedDate());
//        entity.setModifiedDate(dto.getModifiedDate());
//        entity.setClientCode(dto.getClientCode());
//        entity.setCreatedBy(dto.getCreatedBy());
//        entity.setModifiedBy(dto.getModifiedBy());
//        entity.setOnshoreStatus(dto.getOnshoreStatus());
//        entity.setProjectCode(dto.getProjectCode());
//        entity.setReportingLocation(dto.getReportingLocation());
//        entity.setReportingManager(dto.getReportingManager());
//        entity.setStatus(dto.getStatus());
//        entity.setDeleted(dto.getDeleted());
//        entity.setEmployeeBasicDetails(empBasicEntity);
// 
//        return entity;
//    }
//}



package com.pranhirefy.employee.mapper;

import com.pranhirefy.employee.dto.EmployeeProjectDetailsDto;
import com.pranhirefy.employee.entity.EmployeeBasicDetailsEntity;
import com.pranhirefy.employee.entity.EmployeeProjectDetailsEntity;
import org.springframework.stereotype.Component;

@Component
public class EmployeeProjectDetailsMapper {

    public EmployeeProjectDetailsDto toDto(EmployeeProjectDetailsEntity entity) {
        if (entity == null) {
            return null;
        }

        EmployeeProjectDetailsDto dto = new EmployeeProjectDetailsDto();
        dto.setEmpProjectId(entity.getEmpProjectId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setClientCode(entity.getClientCode());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setModifiedBy(entity.getModifiedBy());
        dto.setOnshoreStatus(entity.getOnshoreStatus());
        dto.setProjectCode(entity.getProjectCode());
        dto.setReportingLocation(entity.getReportingLocation());
        dto.setReportingManager(entity.getReportingManager());
        dto.setStatus(entity.getStatus());
        dto.setDeleted(entity.getDeleted());

        if (entity.getEmployeeBasicDetails() != null) {
            dto.setEmployeeBasicDetailId(entity.getEmployeeBasicDetails().getId());
        }

        return dto;
    }

    public EmployeeProjectDetailsEntity toEntity(EmployeeProjectDetailsDto dto) {
        if (dto == null) {
            return null;
        }

        EmployeeProjectDetailsEntity entity = new EmployeeProjectDetailsEntity();
        entity.setEmpProjectId(dto.getEmpProjectId());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setModifiedDate(dto.getModifiedDate());
        entity.setClientCode(dto.getClientCode());
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setModifiedBy(dto.getModifiedBy());
        entity.setOnshoreStatus(dto.getOnshoreStatus());
        entity.setProjectCode(dto.getProjectCode());
        entity.setReportingLocation(dto.getReportingLocation());
        entity.setReportingManager(dto.getReportingManager());
        entity.setStatus(dto.getStatus());
        entity.setDeleted(dto.getDeleted());

        // Set minimal reference to EmployeeBasicDetailsEntity using ID
        if (dto.getEmployeeBasicDetailId() != null) {
            EmployeeBasicDetailsEntity empBasicEntity = new EmployeeBasicDetailsEntity();
            empBasicEntity.setId(dto.getEmployeeBasicDetailId());
            entity.setEmployeeBasicDetails(empBasicEntity);
        }

        return entity;
    }
}
