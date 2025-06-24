//package com.pranhirefy.employee.mapper;
//
//import com.pranhirefy.employee.dto.EmployeeBankDetailsDto;
//import com.pranhirefy.employee.entity.EmployeeBankDetailsEntity;
//import com.pranhirefy.employee.entity.EmployeeBasicDetailsEntity;
//import org.junit.jupiter.api.Test;
//
//import java.util.Date;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class EmployeeBankDetailsMapperTest {
//
//    private final EmployeeBankDetailsMapper mapper = new EmployeeBankDetailsMapper();
//
//    @Test
//    void testToEntity() {
//        EmployeeBankDetailsDto dto = new EmployeeBankDetailsDto();
//        dto.setEmpBankDetailId(1L);
//        dto.setCreatedDate(new Date());
//        dto.setModifiedDate(new Date());
//        dto.setBankIfsc("IFSC0001");
//        dto.setDeleted("No");
//        dto.setAccountType("Savings");
//        dto.setCreatedBy("admin");
//        dto.setModifiedBy("user");
//        dto.setBankAccountNo("1234567890");
//        dto.setEmployeeBasicDetailId(10L);
//
//        EmployeeBankDetailsEntity entity = mapper.toEntity(dto);
//
//        assertEquals(dto.getEmpBankDetailId(), entity.getEmpBankDetailId());
//        assertEquals(dto.getCreatedDate(), entity.getCreatedDate());
//        assertEquals(dto.getModifiedDate(), entity.getModifiedDate());
//        assertEquals(dto.getBankIfsc(), entity.getBankIfsc());
//        assertEquals(dto.getDeleted(), entity.getDeleted());
//        assertEquals(dto.getAccountType(), entity.getAccountType());
//        assertEquals(dto.getCreatedBy(), entity.getCreatedBy());
//        assertEquals(dto.getModifiedBy(), entity.getModifiedBy());
//        assertEquals(dto.getBankAccountNo(), entity.getBankAccountNo());
//        assertEquals(dto.getEmployeeBasicDetailId(), entity.getEmployeeBasicDetails().getId());
//    }
//
//    @Test
//    void testToDto() {
//        EmployeeBankDetailsEntity entity = new EmployeeBankDetailsEntity();
//        entity.setEmpBankDetailId(1L);
//        entity.setCreatedDate(new Date());
//        entity.setModifiedDate(new Date());
//        entity.setBankIfsc("IFSC0002");
//        entity.setDeleted("Yes");
//        entity.setAccountType("Current");
//        entity.setCreatedBy("hr");
//        entity.setModifiedBy("manager");
//        entity.setBankAccountNo("9876543210");
//
//        EmployeeBasicDetailsEntity basic = new EmployeeBasicDetailsEntity();
//        basic.setId(20L);
//        entity.setEmployeeBasicDetails(basic);
//
//        EmployeeBankDetailsDto dto = mapper.toDto(entity);
//
//        assertEquals(entity.getEmpBankDetailId(), dto.getEmpBankDetailId());
//        assertEquals(entity.getCreatedDate(), dto.getCreatedDate());
//        assertEquals(entity.getModifiedDate(), dto.getModifiedDate());
//        assertEquals(entity.getBankIfsc(), dto.getBankIfsc());
//        assertEquals(entity.getDeleted(), dto.getDeleted());
//        assertEquals(entity.getAccountType(), dto.getAccountType());
//        assertEquals(entity.getCreatedBy(), dto.getCreatedBy());
//        assertEquals(entity.getModifiedBy(), dto.getModifiedBy());
//        assertEquals(entity.getBankAccountNo(), dto.getBankAccountNo());
//        assertEquals(entity.getEmployeeBasicDetails().getId(), dto.getEmployeeBasicDetailId());
//    }
//}

package com.pranhirefy.employee.mapper;

import com.pranhirefy.employee.dto.EmployeeBankDetailsDto;
import com.pranhirefy.employee.entity.EmployeeBankDetailsEntity;
import com.pranhirefy.employee.entity.EmployeeBasicDetailsEntity;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeBankDetailsMapperTest {

    private final EmployeeBankDetailsMapper mapper = new EmployeeBankDetailsMapper();

    @Test
    void testParameterizedConstructorAndMapper() {
        // Arrange: Create DTO
        EmployeeBankDetailsDto dto = new EmployeeBankDetailsDto();
        dto.setEmpBankDetailId(1L);
        dto.setCreatedDate(new Date());
        dto.setModifiedDate(new Date());
        dto.setBankIfsc("IFSC123456");
        dto.setDeleted("No");
        dto.setAccountType("Savings");
        dto.setCreatedBy("admin");
        dto.setModifiedBy("admin");
        dto.setBankAccountNo("123456789");
        dto.setEmployeeBasicDetailId(99L);

        // Act: Map DTO to Entity using Mapper
        EmployeeBankDetailsEntity entityFromMapper = mapper.toEntity(dto);

        // Directly use parameterized constructor to cover it
        EmployeeBasicDetailsEntity basic = new EmployeeBasicDetailsEntity();
        basic.setId(dto.getEmployeeBasicDetailId());

        EmployeeBankDetailsEntity entityFromConstructor = new EmployeeBankDetailsEntity(
                dto.getEmpBankDetailId(),
                dto.getCreatedDate(),
                dto.getModifiedDate(),
                dto.getBankIfsc(),
                dto.getDeleted(),
                dto.getAccountType(),
                dto.getCreatedBy(),
                dto.getModifiedBy(),
                dto.getBankAccountNo(),
                basic
        );

        // Assert: Fields match for both paths
        assertEquals(dto.getEmpBankDetailId(), entityFromConstructor.getEmpBankDetailId());
        assertEquals(dto.getBankIfsc(), entityFromConstructor.getBankIfsc());
        assertEquals(dto.getBankAccountNo(), entityFromConstructor.getBankAccountNo());
        assertEquals(dto.getEmployeeBasicDetailId(), entityFromConstructor.getEmployeeBasicDetails().getId());

        assertEquals(dto.getBankIfsc(), entityFromMapper.getBankIfsc());

        // Also cover toDto()
        EmployeeBankDetailsDto mappedBackDto = mapper.toDto(entityFromMapper);
        assertEquals(dto.getBankIfsc(), mappedBackDto.getBankIfsc());
        assertEquals(dto.getBankAccountNo(), mappedBackDto.getBankAccountNo());
        assertEquals(dto.getEmployeeBasicDetailId(), mappedBackDto.getEmployeeBasicDetailId());
    }
    @Test
    void testEmployeeBankDetailsDtoParameterizedConstructor() {
        Date created = new Date();
        Date modified = new Date();

        EmployeeBankDetailsDto dto = new EmployeeBankDetailsDto(
                1L,
                created,
                modified,
                "IFSC123456",
                "Savings",
                "admin",
                "admin",
                "123456789",
                99L
        );

        assertEquals(1L, dto.getEmpBankDetailId());
        assertEquals(created, dto.getCreatedDate());
        assertEquals(modified, dto.getModifiedDate());
        assertEquals("IFSC123456", dto.getBankIfsc());
        assertEquals("Savings", dto.getAccountType());
        assertEquals("admin", dto.getCreatedBy());
        assertEquals("admin", dto.getModifiedBy());
        assertEquals("123456789", dto.getBankAccountNo());
        assertEquals(99L, dto.getEmployeeBasicDetailId());
    }

}

