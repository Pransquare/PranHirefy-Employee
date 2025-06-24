package com.pranhirefy.employee.service.impl;

import com.pranhirefy.employee.dto.EmployeeBankDetailsDto;

import com.pranhirefy.employee.entity.EmployeeBankDetailsEntity;
import com.pranhirefy.employee.exception.InvalidEmployeeBankDetailException;
import com.pranhirefy.employee.exception.InvalidEmployeeBankDetailsException;
import com.pranhirefy.employee.exception.NoBankFoundException;
import com.pranhirefy.employee.mapper.EmployeeBankDetailsMapper;
import com.pranhirefy.employee.repository.EmployeeBankDetailsRepository;
import com.pranhirefy.employee.repository.EmployeeBasicDetailsRepository;
import com.pranhirefy.employee.service.EmployeeBankDetailsService;

import ch.qos.logback.classic.Logger;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeBankDetailsServiceImplTest {
	
	 
    @InjectMocks
    private EmployeeBankDetailsServiceImpl service;
    @Mock
    private Logger logger;

    @Mock
    private EmployeeBankDetailsRepository bankRepository;

    @Mock
    private EmployeeBasicDetailsRepository employeeBasicDetailsRepository;

    @Mock
    private EmployeeBankDetailsMapper mapper;
    
   

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

   // test cases for create
    @Test
    void testCreateEmployeeBank_SuccessfulCreation() {
        // Given
        EmployeeBankDetailsDto inputDto = new EmployeeBankDetailsDto();
        inputDto.setBankIfsc("ABCD0123456");
        inputDto.setAccountType("Savings");
        inputDto.setBankAccountNo("1234567890");
        inputDto.setCreatedBy("admin");
        inputDto.setEmployeeBasicDetailId(1L);

        EmployeeBankDetailsEntity entity = new EmployeeBankDetailsEntity();
        EmployeeBankDetailsEntity savedEntity = new EmployeeBankDetailsEntity();
        EmployeeBankDetailsDto outputDto = new EmployeeBankDetailsDto();

        when(employeeBasicDetailsRepository.existsById(1L)).thenReturn(true);
        when(bankRepository.existsByBankAccountNoAndDeletedIgnoreCase("1234567890", "No")).thenReturn(false);
        when(mapper.toEntity(any())).thenReturn(entity);
        when(bankRepository.save(any())).thenReturn(savedEntity);
        when(mapper.toDto(savedEntity)).thenReturn(outputDto);

        // When
        EmployeeBankDetailsDto result = service.createEmployeeBank(inputDto);

        // Then
        assertNotNull(result);
        verify(bankRepository).save(entity);
    }
    @Test
    void testCreateEmployeeBank_MissingIFSC_ShouldFail() {
        EmployeeBankDetailsDto inputDto = new EmployeeBankDetailsDto();
        inputDto.setAccountType("Savings");
        inputDto.setBankAccountNo("1234567890");
        inputDto.setCreatedBy("admin");
        inputDto.setEmployeeBasicDetailId(1L);

        when(employeeBasicDetailsRepository.existsById(1L)).thenReturn(true);

        InvalidEmployeeBankDetailsException exception = assertThrows(
            InvalidEmployeeBankDetailsException.class,
            () -> service.createEmployeeBank(inputDto)
        );

        assertTrue(exception.getErrors().containsKey("bankIfsc"));
        assertEquals("Validation failed", exception.getMessage());
    }
    @Test
    void testCreateEmployeeBank_InvalidAccountType_ShouldFail() {
        EmployeeBankDetailsDto inputDto = new EmployeeBankDetailsDto();
        inputDto.setBankIfsc("ABCD0123456");
        inputDto.setAccountType("Fixed");  // Invalid
        inputDto.setBankAccountNo("1234567890");
        inputDto.setCreatedBy("admin");
        inputDto.setEmployeeBasicDetailId(1L);

        when(employeeBasicDetailsRepository.existsById(1L)).thenReturn(true);

        InvalidEmployeeBankDetailsException exception = assertThrows(
            InvalidEmployeeBankDetailsException.class,
            () -> service.createEmployeeBank(inputDto)
        );

        assertTrue(exception.getErrors().containsKey("accountType"));
    }
    @Test
    void testCreateEmployeeBank_DuplicateAccountNumber_ShouldFail() {
        EmployeeBankDetailsDto inputDto = new EmployeeBankDetailsDto();
        inputDto.setBankIfsc("ABCD0123456");
        inputDto.setAccountType("Savings");
        inputDto.setBankAccountNo("1234567890");
        inputDto.setCreatedBy("admin");
        inputDto.setEmployeeBasicDetailId(1L);

        when(employeeBasicDetailsRepository.existsById(1L)).thenReturn(true);
        when(bankRepository.existsByBankAccountNoAndDeletedIgnoreCase("1234567890", "No")).thenReturn(true);

        InvalidEmployeeBankDetailsException exception = assertThrows(
            InvalidEmployeeBankDetailsException.class,
            () -> service.createEmployeeBank(inputDto)
        );

        assertTrue(exception.getErrors().containsKey("bankAccountNo"));
    }
    @Test
    void testCreateEmployeeBank_InvalidCreatedBy_ShouldFail() {
        EmployeeBankDetailsDto inputDto = new EmployeeBankDetailsDto();
        inputDto.setBankIfsc("ABCD0123456");
        inputDto.setAccountType("Savings");
        inputDto.setBankAccountNo("1234567890");
        inputDto.setCreatedBy("123Invalid");
        inputDto.setEmployeeBasicDetailId(1L);

        when(employeeBasicDetailsRepository.existsById(1L)).thenReturn(true);
        when(bankRepository.existsByBankAccountNoAndDeletedIgnoreCase("1234567890", "No")).thenReturn(false);

        InvalidEmployeeBankDetailsException exception = assertThrows(
            InvalidEmployeeBankDetailsException.class,
            () -> service.createEmployeeBank(inputDto)
        );

        assertTrue(exception.getErrors().containsKey("createdBy"));
    }
// getAll
    @Test
    void testGetAllEmployeeBank_ShouldReturnList() {
        // Given
        EmployeeBankDetailsEntity entity1 = new EmployeeBankDetailsEntity();
        entity1.setEmpBankDetailId(1L);
        EmployeeBankDetailsEntity entity2 = new EmployeeBankDetailsEntity();
        entity2.setEmpBankDetailId(2L);

        List<EmployeeBankDetailsEntity> entityList = List.of(entity2, entity1); // Assuming descending order

        EmployeeBankDetailsDto dto1 = new EmployeeBankDetailsDto();
        dto1.setEmpBankDetailId(1L);
        EmployeeBankDetailsDto dto2 = new EmployeeBankDetailsDto();
        dto2.setEmpBankDetailId(2L);

        when(bankRepository.findAllByOrderByEmpBankDetailIdDesc()).thenReturn(entityList);
        when(mapper.toDto(entity2)).thenReturn(dto2);
        when(mapper.toDto(entity1)).thenReturn(dto1);

        // When
        List<EmployeeBankDetailsDto> result = service.getAllEmployeeBank();

        // Then
        assertEquals(2, result.size());
        assertEquals(2L, result.get(0).getEmpBankDetailId());
        assertEquals(1L, result.get(1).getEmpBankDetailId());
        verify(bankRepository).findAllByOrderByEmpBankDetailIdDesc();
        verify(mapper, times(2)).toDto(any(EmployeeBankDetailsEntity.class));
    }
    @Test
    void testGetAllEmployeeBank_EmptyList() {
        // Given
        when(bankRepository.findAllByOrderByEmpBankDetailIdDesc()).thenReturn(List.of());

        // When
        List<EmployeeBankDetailsDto> result = service.getAllEmployeeBank();

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(bankRepository).findAllByOrderByEmpBankDetailIdDesc();
        verify(mapper, never()).toDto(any());
    }
// getbyId
    @Test
    void testGetByEmployeeBankDetails_ValidId_ReturnsDto() {
        Long id = 1L;

        EmployeeBankDetailsEntity entity = new EmployeeBankDetailsEntity();
        entity.setEmpBankDetailId(id);
        entity.setDeleted("No");

        EmployeeBankDetailsDto dto = new EmployeeBankDetailsDto();
        dto.setEmpBankDetailId(id);

        when(bankRepository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.toDto(entity)).thenReturn(dto);

        EmployeeBankDetailsDto result = service.getByEmployeeBankDetails(id);

        assertNotNull(result);
        assertEquals(id, result.getEmpBankDetailId());
        verify(bankRepository).findById(id);
        verify(mapper).toDto(entity);
    }
    @Test
    void testGetByEmployeeBankDetails_NullId_ThrowsInvalidException() {
        InvalidEmployeeBankDetailException exception = assertThrows(
            InvalidEmployeeBankDetailException.class,
            () -> service.getByEmployeeBankDetails(null)
        );

        assertEquals("Invalid ID: must be greater than 0.", exception.getMessage());
    }
    @Test
    void testGetByEmployeeBankDetails_InvalidId_ThrowsInvalidException() {
        InvalidEmployeeBankDetailException exception = assertThrows(
            InvalidEmployeeBankDetailException.class,
            () -> service.getByEmployeeBankDetails(0L)
        );

        assertEquals("Invalid ID: must be greater than 0.", exception.getMessage());
    }
    @Test
    void testGetByEmployeeBankDetails_EntityNotFound_ThrowsNoBankFound() {
        Long id = 100L;

        when(bankRepository.findById(id)).thenReturn(Optional.empty());

        NoBankFoundException exception = assertThrows(
            NoBankFoundException.class,
            () -> service.getByEmployeeBankDetails(id)
        );

        assertEquals("Employee bank details with ID 100 not found", exception.getMessage());
    }
    @Test
    void testGetByEmployeeBankDetails_SoftDeletedEntity_ThrowsNoBankFound() {
        Long id = 10L;

        EmployeeBankDetailsEntity entity = new EmployeeBankDetailsEntity();
        entity.setEmpBankDetailId(id);
        entity.setDeleted("Yes");

        when(bankRepository.findById(id)).thenReturn(Optional.of(entity));

        NoBankFoundException exception = assertThrows(
            NoBankFoundException.class,
            () -> service.getByEmployeeBankDetails(id)
        );

        assertEquals("Employee bank details with ID 10 is already deleted.", exception.getMessage());
    }
    @Test
    void testUpdateEmployeeBankDetails_SuccessfulUpdate() {
        Long id = 1L;

        // Input DTO
        EmployeeBankDetailsDto inputDto = new EmployeeBankDetailsDto();
        inputDto.setBankIfsc("ABCD0123456");
        inputDto.setAccountType("Savings");
        inputDto.setBankAccountNo("1234567890");
        inputDto.setEmployeeBasicDetailId(10L);

        // Existing entity
        EmployeeBankDetailsEntity existingEntity = new EmployeeBankDetailsEntity();
        existingEntity.setEmpBankDetailId(id);
        existingEntity.setCreatedBy("admin");
        existingEntity.setCreatedDate(new Date());
        existingEntity.setBankIfsc("OLD0123456");
        existingEntity.setAccountType("Current");
        existingEntity.setBankAccountNo("0987654321");
        existingEntity.setDeleted("No");

        // Updated entity & DTO
        EmployeeBankDetailsEntity updatedEntity = new EmployeeBankDetailsEntity();
        updatedEntity.setEmpBankDetailId(id);

        EmployeeBankDetailsDto expectedDto = new EmployeeBankDetailsDto();
        expectedDto.setEmpBankDetailId(id);

        // Mocks
        when(bankRepository.findById(id)).thenReturn(Optional.of(existingEntity));
        when(employeeBasicDetailsRepository.existsById(10L)).thenReturn(true);
        when(bankRepository.existsByBankAccountNoAndDeletedIgnoreCase("1234567890", "No")).thenReturn(false);
        when(bankRepository.save(any())).thenReturn(updatedEntity);
        when(mapper.toDto(updatedEntity)).thenReturn(expectedDto);

        // Test
        EmployeeBankDetailsDto result = service.updateEmployeeBankDetails(id, inputDto);

        assertNotNull(result);
        assertEquals(id, result.getEmpBankDetailId());
        verify(bankRepository).save(any(EmployeeBankDetailsEntity.class));
    }
    @Test
    void testUpdateEmployeeBankDetails_NullId_ThrowsInvalidIdException() {
        EmployeeBankDetailsDto dto = new EmployeeBankDetailsDto();
        InvalidEmployeeBankDetailException exception = assertThrows(
            InvalidEmployeeBankDetailException.class,
            () -> service.updateEmployeeBankDetails(null, dto)
        );
        assertEquals("Invalid ID: must be greater than 0.", exception.getMessage());
    }
    @Test
    void testUpdateEmployeeBankDetails_ZeroId_ThrowsInvalidIdException() {
        EmployeeBankDetailsDto dto = new EmployeeBankDetailsDto();
        InvalidEmployeeBankDetailException exception = assertThrows(
            InvalidEmployeeBankDetailException.class,
            () -> service.updateEmployeeBankDetails(0L, dto)
        );
        assertEquals("Invalid ID: must be greater than 0.", exception.getMessage());
    }
    @Test
    void testUpdateEmployeeBankDetails_InvalidInput_ShouldThrowValidationException() {
        Long id = 1L;

        // Create DTO with invalid fields
        EmployeeBankDetailsDto dto = new EmployeeBankDetailsDto();
        dto.setEmpBankDetailId(id);                 // Required
        dto.setBankIfsc("123");                     // Invalid IFSC
        dto.setAccountType("InvalidType");          // Not Savings/Current/Salary
        dto.setBankAccountNo("abc");                // Invalid account number
        dto.setEmployeeBasicDetailId(10L);          // FK, mock exists

        when(employeeBasicDetailsRepository.existsById(10L)).thenReturn(true);

        InvalidEmployeeBankDetailsException ex = assertThrows(
            InvalidEmployeeBankDetailsException.class,
            () -> service.updateEmployeeBankDetails(id, dto)
        );

        assertEquals("Validation failed", ex.getMessage());
        assertTrue(ex.getErrors().containsKey("bankIfsc"));
        assertTrue(ex.getErrors().containsKey("accountType"));
        assertTrue(ex.getErrors().containsKey("bankAccountNo"));
    }

// delete
    @Test
    void testDeleteByEmployeeBankDetails_InvalidId_ShouldThrowException() {
        InvalidEmployeeBankDetailException ex = assertThrows(
            InvalidEmployeeBankDetailException.class,
            () -> service.deleteByEmployeeBankDetails(0L)
        );
        assertEquals("Invalid ID: must be greater than 0.", ex.getMessage());
    }

    @Test
    void testDeleteByEmployeeBankDetails_EntityNotFound_ShouldThrowException() {
        Long id = 1L;
        when(bankRepository.findById(id)).thenReturn(Optional.empty());

        NoBankFoundException ex = assertThrows(
            NoBankFoundException.class,
            () -> service.deleteByEmployeeBankDetails(id)
        );

        assertEquals("Employee bank details with ID " + id + " not found", ex.getMessage());
    }

    @Test
    void testDeleteByEmployeeBankDetails_AlreadyDeleted_ShouldThrowException() {
        Long id = 1L;

        EmployeeBankDetailsEntity entity = new EmployeeBankDetailsEntity();
        entity.setDeleted("Yes");

        when(bankRepository.findById(id)).thenReturn(Optional.of(entity));

        NoBankFoundException ex = assertThrows(
            NoBankFoundException.class,
            () -> service.deleteByEmployeeBankDetails(id)
        );

        assertEquals("Bank detail with ID " + id + " is already deleted.", ex.getMessage());
    }
    @Test
    void testDeleteByEmployeeBankDetails_SuccessfulSoftDelete() {
        Long id = 1L;

        EmployeeBankDetailsEntity entity = Mockito.spy(new EmployeeBankDetailsEntity());
        entity.setDeleted("No");

        when(bankRepository.findById(id)).thenReturn(Optional.of(entity));

        service.deleteByEmployeeBankDetails(id);

        verify(entity).softDelete();
        verify(bankRepository).save(entity);
    }
// validate
    @Test
    void testValidationFails_WhenDtoIsNull() {
        Map<String, String> errors = new HashMap<>();
        service.validateEmployeeBankDetailsDto(null, errors, true);

        assertTrue(errors.containsKey("body"));
        assertEquals("Request body is required.", errors.get("body"));
    }
    @Test
    void testValidationFails_WhenIfscIsMissing() {
        EmployeeBankDetailsDto dto = new EmployeeBankDetailsDto();
        dto.setBankIfsc("  "); // empty after trim
        dto.setAccountType("Savings");
        dto.setBankAccountNo("1234567890");
        dto.setEmployeeBasicDetailId(1L);

        when(employeeBasicDetailsRepository.existsById(1L)).thenReturn(true);

        Map<String, String> errors = new HashMap<>();
        service.validateEmployeeBankDetailsDto(dto, errors, true);

        assertTrue(errors.containsKey("bankIfsc"));
        assertEquals("Bank IFSC is required", errors.get("bankIfsc"));
    }
    @Test
    void testValidationFails_WhenIfscIsInvalidFormat() {
        EmployeeBankDetailsDto dto = new EmployeeBankDetailsDto();
        dto.setBankIfsc("12345");
        dto.setAccountType("Savings");
        dto.setBankAccountNo("1234567890");
        dto.setEmployeeBasicDetailId(1L);

        when(employeeBasicDetailsRepository.existsById(1L)).thenReturn(true);

        Map<String, String> errors = new HashMap<>();
        service.validateEmployeeBankDetailsDto(dto, errors, true);

        assertTrue(errors.containsKey("bankIfsc"));
        assertEquals("Invalid IFSC format (e.g., ABCD0123456).", errors.get("bankIfsc"));
    }
    @Test
    void testValidationFails_WhenAccountTypeMissing() {
        EmployeeBankDetailsDto dto = new EmployeeBankDetailsDto();
        dto.setBankIfsc("ABCD0123456");
        dto.setAccountType(" ");
        dto.setBankAccountNo("1234567890");
        dto.setEmployeeBasicDetailId(1L);

        when(employeeBasicDetailsRepository.existsById(1L)).thenReturn(true);

        Map<String, String> errors = new HashMap<>();
        service.validateEmployeeBankDetailsDto(dto, errors, true);

        assertTrue(errors.containsKey("accountType"));
        assertEquals("Account type is required", errors.get("accountType"));
    }
    @Test
    void testValidationFails_WhenAccountNumberMissing() {
        EmployeeBankDetailsDto dto = new EmployeeBankDetailsDto();
        dto.setBankIfsc("ABCD0123456");
        dto.setAccountType("Savings");
        dto.setBankAccountNo(null);
        dto.setEmployeeBasicDetailId(1L);

        when(employeeBasicDetailsRepository.existsById(1L)).thenReturn(true);

        Map<String, String> errors = new HashMap<>();
        service.validateEmployeeBankDetailsDto(dto, errors, true);

        assertTrue(errors.containsKey("bankAccountNo"));
        assertEquals("Bank account number is required.", errors.get("bankAccountNo"));
    }
    @Test
    void testValidationFails_WhenAccountNumberHasSpaces() {
        EmployeeBankDetailsDto dto = new EmployeeBankDetailsDto();
        dto.setBankIfsc("ABCD0123456");
        dto.setAccountType("Savings");
        dto.setBankAccountNo("1234 567890");
        dto.setEmployeeBasicDetailId(1L);

        when(employeeBasicDetailsRepository.existsById(1L)).thenReturn(true);

        Map<String, String> errors = new HashMap<>();
        service.validateEmployeeBankDetailsDto(dto, errors, true);

        assertTrue(errors.containsKey("bankAccountNo"));
        assertEquals("Bank account number must not contain spaces.", errors.get("bankAccountNo"));
    }
    @Test
    void testInvalidModifiedByFormat_OnUpdate() {
        EmployeeBankDetailsDto dto = new EmployeeBankDetailsDto();
        dto.setModifiedBy("123Invalid"); // Invalid format
        dto.setEmployeeBasicDetailId(1L);
        
        when(employeeBasicDetailsRepository.existsById(1L)).thenReturn(true);

        Map<String, String> errors = new HashMap<>();
        service.validateEmployeeBankDetailsDto(dto, errors, false);

        assertTrue(errors.containsKey("modifiedBy"));
        assertEquals("ModifiedBy should start with a letter and contain only letters, dots, and spaces.", errors.get("modifiedBy"));
    }
    @Test
    void testValidationFails_WhenEmployeeBasicDetailIdIsNull() {
        EmployeeBankDetailsDto dto = new EmployeeBankDetailsDto();
        Map<String, String> errors = new HashMap<>();

        service.validateEmployeeBankDetailsDto(dto, errors, true);

        assertTrue(errors.containsKey("employeeBasicDetailId"));
        assertEquals("EmployeeBasicDetailId is required.", errors.get("employeeBasicDetailId"));
    }
    @Test
    void testValidationFails_WhenEmployeeBasicDetailIdNotExist() {
        EmployeeBankDetailsDto dto = new EmployeeBankDetailsDto();
        dto.setEmployeeBasicDetailId(999L);

        when(employeeBasicDetailsRepository.existsById(999L)).thenReturn(false);

        Map<String, String> errors = new HashMap<>();
        service.validateEmployeeBankDetailsDto(dto, errors, true);

        assertTrue(errors.containsKey("employeeBasicDetailId"));
        assertEquals("No employee exists with ID 999", errors.get("employeeBasicDetailId"));
    }
    @Test
    void testModifiedByProvidedDuringCreation() {
        EmployeeBankDetailsDto dto = new EmployeeBankDetailsDto();
        dto.setModifiedBy("admin");
        dto.setEmployeeBasicDetailId(1L);

        when(employeeBasicDetailsRepository.existsById(1L)).thenReturn(true);

        Map<String, String> errors = new HashMap<>();
        service.validateEmployeeBankDetailsDto(dto, errors, true);

        assertTrue(errors.containsKey("modifiedBy"));
        assertEquals("Do not provide ModifiedBy during creation.", errors.get("modifiedBy"));
    }
    @Test
    void testModifiedDateProvidedDuringCreation() {
        EmployeeBankDetailsDto dto = new EmployeeBankDetailsDto();
        dto.setModifiedDate(new Date());
        dto.setEmployeeBasicDetailId(1L);

        when(employeeBasicDetailsRepository.existsById(1L)).thenReturn(true);

        Map<String, String> errors = new HashMap<>();
        service.validateEmployeeBankDetailsDto(dto, errors, true);

        assertTrue(errors.containsKey("modifiedDate"));
        assertEquals("Do not provide ModifiedDate during creation.", errors.get("modifiedDate"));
    }
    @Test
    void testCreatedDateProvidedDuringCreation() {
        EmployeeBankDetailsDto dto = new EmployeeBankDetailsDto();
        dto.setCreatedDate(new Date());
        dto.setEmployeeBasicDetailId(1L);

        when(employeeBasicDetailsRepository.existsById(1L)).thenReturn(true);

        Map<String, String> errors = new HashMap<>();
        service.validateEmployeeBankDetailsDto(dto, errors, true);

        assertTrue(errors.containsKey("createdDate"));
        assertEquals("Do not provide CreatedDate during creation.", errors.get("createdDate"));
    }
    @Test
    void testDeletedNotNoDuringCreation() {
        EmployeeBankDetailsDto dto = new EmployeeBankDetailsDto();
        dto.setDeleted("Yes");
        dto.setEmployeeBasicDetailId(1L);

        when(employeeBasicDetailsRepository.existsById(1L)).thenReturn(true);

        Map<String, String> errors = new HashMap<>();
        service.validateEmployeeBankDetailsDto(dto, errors, true);

        assertTrue(errors.containsKey("deleted"));
        assertEquals("Only 'No' is allowed for 'deleted' during creation.", errors.get("deleted"));
    }
    @Test
    void testIdProvidedDuringCreation_ThrowsException() {
        EmployeeBankDetailsDto dto = new EmployeeBankDetailsDto();
        dto.setEmpBankDetailId(1L);
        dto.setEmployeeBasicDetailId(1L);

        when(employeeBasicDetailsRepository.existsById(1L)).thenReturn(true);

        InvalidEmployeeBankDetailException ex = assertThrows(
            InvalidEmployeeBankDetailException.class,
            () -> service.validateEmployeeBankDetailsDto(dto, new HashMap<>(), true)
        );

        assertEquals("ID must not be set while creating new bank detail.", ex.getMessage());
    }
   
    @Test
    void testValidateUpdate_ImmutableCreatedByChanged() {
        EmployeeBankDetailsDto dto = new EmployeeBankDetailsDto();
        dto.setEmpBankDetailId(1L);
        dto.setCreatedBy("newUser");
        dto.setBankAccountNo("123456789");
        dto.setBankIfsc("ABCD0123456");
        dto.setAccountType("Savings");
        dto.setEmployeeBasicDetailId(100L);

        EmployeeBankDetailsEntity existing = new EmployeeBankDetailsEntity();
        existing.setDeleted("No");
        existing.setCreatedBy("originalUser");
        existing.setBankAccountNo("123456789");
        existing.setBankIfsc("ABCD0123456");
        existing.setAccountType("Savings");

        Map<String, String> errors = new HashMap<>();
        when(bankRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(employeeBasicDetailsRepository.existsById(100L)).thenReturn(true);

        service.validateEmployeeBankDetailsDto(dto, errors, false);

        assertEquals("createdBy cannot be modified once set.", errors.get("createdBy"));
    }
    @Test
    void testValidateUpdate_ImmutableCreatedDateChanged() {
        EmployeeBankDetailsDto dto = new EmployeeBankDetailsDto();
        dto.setEmpBankDetailId(1L);
        dto.setCreatedDate(new Date());
        dto.setBankAccountNo("123456789");         // Required to prevent NPE
        dto.setBankIfsc("ABCD0123456");            // Required
        dto.setAccountType("Savings");             // Required
        dto.setEmployeeBasicDetailId(100L);        // Required

        EmployeeBankDetailsEntity existing = new EmployeeBankDetailsEntity();
        existing.setDeleted("No");
        existing.setCreatedDate(new Date(0)); // Different date
        existing.setBankAccountNo("123456789");
        existing.setBankIfsc("ABCD0123456");
        existing.setAccountType("Savings");

        Map<String, String> errors = new HashMap<>();
        when(bankRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(employeeBasicDetailsRepository.existsById(100L)).thenReturn(true);

        service.validateEmployeeBankDetailsDto(dto, errors, false);

        assertEquals("createdDate cannot be modified once set.", errors.get("createdDate"));
    }
    @Test
    void testValidateUpdate_ImmutableDeletedFieldChanged() {
        EmployeeBankDetailsDto dto = new EmployeeBankDetailsDto();
        dto.setEmpBankDetailId(1L);
        dto.setDeleted("yes");
        dto.setBankAccountNo("123456789");        // Required to avoid NPE
        dto.setBankIfsc("ABCD0123456");           // Required
        dto.setAccountType("Savings");            // Required
        dto.setEmployeeBasicDetailId(100L);       // Required

        EmployeeBankDetailsEntity existing = new EmployeeBankDetailsEntity();
        existing.setDeleted("no");
        existing.setBankAccountNo("123456789");
        existing.setBankIfsc("ABCD0123456");
        existing.setAccountType("Savings");

        Map<String, String> errors = new HashMap<>();
        when(bankRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(employeeBasicDetailsRepository.existsById(100L)).thenReturn(true);

        service.validateEmployeeBankDetailsDto(dto, errors, false);

        assertEquals("deleted field cannot be modified.", errors.get("deleted"));
    }
    @Test
    void testValidateUpdate_ModifiedDateProvided_ShouldFail() {
        EmployeeBankDetailsDto dto = new EmployeeBankDetailsDto();
        dto.setEmpBankDetailId(1L);
        dto.setModifiedDate(new Date());
        dto.setBankAccountNo("123456789");
        dto.setBankIfsc("ABCD0123456");
        dto.setAccountType("Savings");
        dto.setEmployeeBasicDetailId(100L);

        EmployeeBankDetailsEntity existing = new EmployeeBankDetailsEntity();
        existing.setDeleted("No");
        existing.setBankAccountNo("000000000");
        existing.setBankIfsc("WXYZ0123456");
        existing.setAccountType("Current");

        Map<String, String> errors = new HashMap<>();
        when(bankRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(employeeBasicDetailsRepository.existsById(100L)).thenReturn(true);

        service.validateEmployeeBankDetailsDto(dto, errors, false);

        assertEquals("modifiedDate is set automatically. Do not provide it in the request.", errors.get("modifiedDate"));
    }
    @Test
    void testValidateUpdate_ModifiedByProvided_ShouldFail() {
        EmployeeBankDetailsDto dto = new EmployeeBankDetailsDto();
        dto.setEmpBankDetailId(1L);
        dto.setModifiedBy("admin");
        dto.setBankAccountNo("123456789");
        dto.setBankIfsc("ABCD0123456");
        dto.setAccountType("Savings");
        dto.setEmployeeBasicDetailId(100L);

        EmployeeBankDetailsEntity existing = new EmployeeBankDetailsEntity();
        existing.setDeleted("No");
        existing.setBankAccountNo("000000000");
        existing.setBankIfsc("WXYZ0123456");
        existing.setAccountType("Current");

        Map<String, String> errors = new HashMap<>();
        when(bankRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(employeeBasicDetailsRepository.existsById(100L)).thenReturn(true);

        service.validateEmployeeBankDetailsDto(dto, errors, false);

        assertEquals("modifiedBy is set automatically. Do not provide it in the request.", errors.get("modifiedBy"));
    }
    @Test
    void testValidateUpdate_SoftDeletedEntity_ShouldFail() {
        EmployeeBankDetailsDto dto = new EmployeeBankDetailsDto();
        dto.setEmpBankDetailId(1L);
        dto.setBankIfsc("ABCD0123456");
        dto.setAccountType("Savings");
        dto.setBankAccountNo("123456789");
        dto.setEmployeeBasicDetailId(100L);

        EmployeeBankDetailsEntity existing = new EmployeeBankDetailsEntity();
        existing.setDeleted("yes");  // soft-deleted

        Map<String, String> errors = new HashMap<>();
        when(bankRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(employeeBasicDetailsRepository.existsById(100L)).thenReturn(true);

        service.validateEmployeeBankDetailsDto(dto, errors, false);

        assertEquals("Cannot update a soft-deleted bank detail.", errors.get("deleted"));
    }
//update

    

    
    
    
    @Test
    void testUpdateEmployeeBankDetails_WhenBankDetailNotFound_ShouldThrow() {
        // Given
        Long id = 999L;

        EmployeeBankDetailsDto dto = new EmployeeBankDetailsDto();
        dto.setBankIfsc("ABCD0123456");
        dto.setAccountType("Savings");
        dto.setBankAccountNo("123456789012");
        dto.setCreatedBy("admin");
        dto.setCreatedDate(new Date());
        dto.setDeleted("No");
        dto.setEmployeeBasicDetailId(1L);  // Assume this exists

        // Mock employeeBasicDetailsRepository.existsById() to pass validation
        when(employeeBasicDetailsRepository.existsById(1L)).thenReturn(true);

        // Mock bankRepository.findById() to return empty (simulate not found)
        when(bankRepository.findById(id)).thenReturn(Optional.empty());

        // When + Then
        NoBankFoundException exception = assertThrows(NoBankFoundException.class, () -> {
            service.updateEmployeeBankDetails(id, dto);
        });

        assertEquals("Employee bank details with ID " + id + " not found", exception.getMessage());
    }





    










}
