package com.pranhirefy.employee.mapper;

import com.pranhirefy.employee.dto.EmployeeBankDetailsDto;
import com.pranhirefy.employee.entity.EmployeeBankDetailsEntity;
import com.pranhirefy.employee.entity.EmployeeBasicDetailsEntity;
import org.springframework.stereotype.Component;

@Component
public class EmployeeBankDetailsMapper {

    public EmployeeBankDetailsEntity toEntity(EmployeeBankDetailsDto dto) {
        EmployeeBankDetailsEntity entity = new EmployeeBankDetailsEntity();
        entity.setEmpBankDetailId(dto.getEmpBankDetailId());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setModifiedDate(dto.getModifiedDate());
        entity.setBankIfsc(dto.getBankIfsc());
        entity.setDeleted(dto.getDeleted());
        entity.setAccountType(dto.getAccountType());
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setModifiedBy(dto.getModifiedBy());
        entity.setBankAccountNo(dto.getBankAccountNo());

        EmployeeBasicDetailsEntity basic = new EmployeeBasicDetailsEntity();
        basic.setId(dto.getEmployeeBasicDetailId());
        entity.setEmployeeBasicDetails(basic);
        
      

        return entity;
    }

    public EmployeeBankDetailsDto toDto(EmployeeBankDetailsEntity entity) {
        EmployeeBankDetailsDto dto = new EmployeeBankDetailsDto();
        dto.setEmpBankDetailId(entity.getEmpBankDetailId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setBankIfsc(entity.getBankIfsc());
        dto.setDeleted(entity.getDeleted());
        dto.setAccountType(entity.getAccountType());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setModifiedBy(entity.getModifiedBy());
        dto.setBankAccountNo(entity.getBankAccountNo());

        if (entity.getEmployeeBasicDetails() != null) {
            dto.setEmployeeBasicDetailId(entity.getEmployeeBasicDetails().getId());
        }

        return dto;
    }
}

