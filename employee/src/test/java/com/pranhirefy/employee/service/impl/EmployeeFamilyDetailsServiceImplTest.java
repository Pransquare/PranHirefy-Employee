package com.pranhirefy.employee.service.impl;

import com.pranhirefy.employee.dto.EmployeeFamilyDetailsDTO;
import com.pranhirefy.employee.entity.*;
import com.pranhirefy.employee.exception.*;
import com.pranhirefy.employee.mapper.EmployeeFamilyDetailsMapper;
import com.pranhirefy.employee.repository.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.*;
import java.time.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Optional;

@SpringBootTest
public class EmployeeFamilyDetailsServiceImplTest {

    @MockBean
    private EmployeeFamilyDetailsRepository repository;

    @MockBean
    private EmployeeBasicDetailsRepository basicRepo;

    @Autowired
    private EmployeeFamilyDetailsServiceImpl service;

    @Test
    void testSaveOrUpdate_Create() {
        EmployeeFamilyDetailsDTO dto = new EmployeeFamilyDetailsDTO();
        dto.setName("John");
        dto.setRelation("Brother");
        dto.setCreatedBy("Admin");
        dto.setIsInsuranceRequired("Yes");
        dto.setDob(LocalDateTime.now().minusDays(1));
        dto.setEmpBasicDetailId(1L);

        EmployeeBasicDetailsEntity basicEntity = new EmployeeBasicDetailsEntity();
        when(basicRepo.findById(1L)).thenReturn(Optional.of(basicEntity));

        EmployeeFamilyDetails entity = new EmployeeFamilyDetails();
        entity.setEmpFamilyDetailId(1L);
        when(repository.save(any(EmployeeFamilyDetails.class))).thenReturn(entity);

        EmployeeFamilyDetailsDTO saved = service.saveOrUpdate(dto);
        assertNotNull(saved);
    }

    @Test
    void testSaveOrUpdate_ValidationFail() {
        EmployeeFamilyDetailsDTO dto = new EmployeeFamilyDetailsDTO();
        dto.setName("");
        assertThrows(CombinedValidationException.class, () -> service.saveOrUpdate(dto));
    }

    @Test
    void testGetAll() {
        EmployeeFamilyDetails entity = new EmployeeFamilyDetails();
        entity.setName("John");
        when(repository.findByIsDeletedOrderByEmpFamilyDetailIdDesc("No"))
                .thenReturn(List.of(entity));

        List<EmployeeFamilyDetailsDTO> result = service.getAll();
        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getName());
    }

    @Test
    void testGetById_Found1() {
        EmployeeFamilyDetails entity = new EmployeeFamilyDetails();
        entity.setName("John");
        when(repository.findByEmpFamilyDetailIdAndIsDeleted(1L, "No"))
                .thenReturn(Optional.of(entity));

        EmployeeFamilyDetailsDTO result = service.getById(1L);
        assertEquals("John", result.getName());
    }

    @Test
    void testGetById_NotFound() {
        when(repository.findByEmpFamilyDetailIdAndIsDeleted(1L, "No"))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.getById(1L));
    }

    @Test
    void testDelete_Found() {
        EmployeeFamilyDetails entity = new EmployeeFamilyDetails();
        entity.setEmpFamilyDetailId(1L);
        entity.setIsDeleted("No");

        when(repository.findByEmpFamilyDetailIdAndIsDeleted(1L, "No"))
                .thenReturn(Optional.of(entity));

        service.delete(1L);
        verify(repository).save(entity);
    }
    
    
    @Test
    void testSaveOrUpdate_NullDto_ShouldThrowEmptyRequestBodyException() {
        assertThrows(EmptyRequestBodyException.class, () -> service.saveOrUpdate(null));
    }

    // ✅ 2. Validation Failure (Missing Name, Relation, DOB, etc.)
    @Test
    void testSaveOrUpdate_ValidationFailure_ShouldThrowCombinedValidationException() {
        EmployeeFamilyDetailsDTO dto = new EmployeeFamilyDetailsDTO();
        dto.setEmpBasicDetailId(1L);
        dto.setName(""); // Will cause error
        assertThrows(CombinedValidationException.class, () -> service.saveOrUpdate(dto));
    }

    // ✅ 3. Employee Not Found
    @Test
    void testSaveOrUpdate_EmployeeNotFound_ShouldThrowResourceNotFoundException() {
        EmployeeFamilyDetailsDTO dto = new EmployeeFamilyDetailsDTO();
        dto.setEmpBasicDetailId(99L);
        dto.setName("John");
        dto.setRelation("Brother");
        dto.setIsInsuranceRequired("Yes");
        dto.setDob(LocalDateTime.of(2000, 1, 1, 0, 0));
        dto.setCreatedBy("Admin");

        when(basicRepo.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.saveOrUpdate(dto));
    }

    // ✅ 4. Save New Employee Family Details (Create Scenario)
    @Test
    void testSaveOrUpdate_CreateSuccess() {
        EmployeeFamilyDetailsDTO dto = new EmployeeFamilyDetailsDTO();
        dto.setEmpBasicDetailId(1L);
        dto.setName("John");
        dto.setRelation("Brother");
        dto.setIsInsuranceRequired("Yes");
        dto.setDob(LocalDateTime.of(2000, 1, 1, 0, 0));
        dto.setCreatedBy("Admin");

        EmployeeBasicDetailsEntity employee = new EmployeeBasicDetailsEntity();
        when(basicRepo.findById(1L)).thenReturn(Optional.of(employee));

        EmployeeFamilyDetails savedEntity = new EmployeeFamilyDetails();
        savedEntity.setEmpFamilyDetailId(123L);
        when(repository.save(any(EmployeeFamilyDetails.class))).thenReturn(savedEntity);

        EmployeeFamilyDetailsDTO result = service.saveOrUpdate(dto);
        assertEquals(123L, result.getEmpFamilyDetailId());
    }

    // ✅ 5. Update Employee Family Details
    @Test
    void testSaveOrUpdate_UpdateSuccess() {
        EmployeeFamilyDetailsDTO dto = new EmployeeFamilyDetailsDTO();
        dto.setEmpFamilyDetailId(123L);
        dto.setEmpBasicDetailId(1L);
        dto.setName("John");
        dto.setRelation("Brother");
        dto.setIsInsuranceRequired("Yes");
        dto.setDob(LocalDateTime.of(2000, 1, 1, 0, 0));
        dto.setModifiedBy("Editor");

        EmployeeBasicDetailsEntity employee = new EmployeeBasicDetailsEntity();
        when(basicRepo.findById(1L)).thenReturn(Optional.of(employee));

        EmployeeFamilyDetails existing = new EmployeeFamilyDetails();
        existing.setCreatedDate(LocalDateTime.now());
        existing.setCreatedBy("Admin");
        existing.setIsDeleted("No");
        when(repository.findById(123L)).thenReturn(Optional.of(existing));

        EmployeeFamilyDetails savedEntity = new EmployeeFamilyDetails();
        savedEntity.setEmpFamilyDetailId(123L);
        when(repository.save(any(EmployeeFamilyDetails.class))).thenReturn(savedEntity);

        EmployeeFamilyDetailsDTO result = service.saveOrUpdate(dto);
        assertEquals(123L, result.getEmpFamilyDetailId());
    }

    // ✅ 6. Get All Results
    @Test
    void testGetAll_ShouldReturnList() {
        EmployeeFamilyDetails entity = new EmployeeFamilyDetails();
        entity.setEmpFamilyDetailId(1L);
        when(repository.findByIsDeletedOrderByEmpFamilyDetailIdDesc("No"))
                .thenReturn(List.of(entity));

        List<EmployeeFamilyDetailsDTO> results = service.getAll();
        assertEquals(1, results.size());
    }

    // ✅ 7. Get By Id (Invalid Id)
    @Test
    void testGetById_InvalidId_ShouldThrowBadRequestException() {
        assertThrows(BadRequestException.class, () -> service.getById(0L));
    }

    // ✅ 8. Get By Id (Not Found)
    @Test
    void testGetById_NotFound_ShouldThrowResourceNotFoundException() {
        when(repository.findByEmpFamilyDetailIdAndIsDeleted(1L, "No"))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.getById(1L));
    }

    // ✅ 9. Get By Id (Found)
    @Test
    void testGetById_Found() {
        EmployeeFamilyDetails entity = new EmployeeFamilyDetails();
        entity.setEmpFamilyDetailId(1L);
        when(repository.findByEmpFamilyDetailIdAndIsDeleted(1L, "No"))
                .thenReturn(Optional.of(entity));

        EmployeeFamilyDetailsDTO result = service.getById(1L);
        assertEquals(1L, result.getEmpFamilyDetailId());
    }

    // ✅ 10. Delete Invalid Id
    @Test
    void testDelete_InvalidId_ShouldThrowBadRequestException() {
        assertThrows(BadRequestException.class, () -> service.delete(0L));
    }

    // ✅ 11. Delete Not Found
    @Test
    void testDelete_NotFound_ShouldThrowResourceNotFoundException() {
        when(repository.findByEmpFamilyDetailIdAndIsDeleted(1L, "No"))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.delete(1L));
    }

    // ✅ 12. Delete Success
    @Test
    void testDelete_Success() {
        EmployeeFamilyDetails entity = new EmployeeFamilyDetails();
        entity.setIsDeleted("No");
        when(repository.findByEmpFamilyDetailIdAndIsDeleted(1L, "No"))
                .thenReturn(Optional.of(entity));

        service.delete(1L);

        assertEquals("Yes", entity.getIsDeleted());
        verify(repository, times(1)).save(entity);
    }

    // ✅ 13. Search Results
    @Test
    void testSearch_ShouldReturnList() {
        EmployeeFamilyDetails entity = new EmployeeFamilyDetails();
        entity.setEmpFamilyDetailId(1L);
        when(repository.searchByNameAndRelation("John", "Brother"))
                .thenReturn(List.of(entity));

        List<EmployeeFamilyDetailsDTO> results = service.search("John", "Brother");
        assertEquals(1, results.size());
    }
 // ✅ Name is required
    @Test
    void testValidation_NameRequiredError() {
        EmployeeFamilyDetailsDTO dto = new EmployeeFamilyDetailsDTO();
        dto.setEmpBasicDetailId(1L);
        dto.setName(""); // Will fail
        dto.setRelation("Brother");
        dto.setIsInsuranceRequired("Yes");
        dto.setDob(LocalDateTime.of(2000,1,1,0,0));
        dto.setCreatedBy("Admin");

        assertThrows(CombinedValidationException.class, () -> service.saveOrUpdate(dto));
    }

    // ✅ Name Pattern Error
    @Test
    void testValidation_NamePatternError() {
        EmployeeFamilyDetailsDTO dto = new EmployeeFamilyDetailsDTO();
        dto.setEmpBasicDetailId(1L);
        dto.setName("123Invalid"); // Will fail
        dto.setRelation("Brother");
        dto.setIsInsuranceRequired("Yes");
        dto.setDob(LocalDateTime.of(2000,1,1,0,0));
        dto.setCreatedBy("Admin");

        assertThrows(CombinedValidationException.class, () -> service.saveOrUpdate(dto));
    }

    // ✅ Relation is required
    @Test
    void testValidation_RelationRequiredError() {
        EmployeeFamilyDetailsDTO dto = new EmployeeFamilyDetailsDTO();
        dto.setEmpBasicDetailId(1L);
        dto.setName("John");
        dto.setRelation(""); // Will fail
        dto.setIsInsuranceRequired("Yes");
        dto.setDob(LocalDateTime.of(2000,1,1,0,0));
        dto.setCreatedBy("Admin");

        assertThrows(CombinedValidationException.class, () -> service.saveOrUpdate(dto));
    }

    // ✅ Relation Pattern Error
    @Test
    void testValidation_RelationPatternError() {
        EmployeeFamilyDetailsDTO dto = new EmployeeFamilyDetailsDTO();
        dto.setEmpBasicDetailId(1L);
        dto.setName("John");
        dto.setRelation("123Invalid"); // Will fail
        dto.setIsInsuranceRequired("Yes");
        dto.setDob(LocalDateTime.of(2000,1,1,0,0));
        dto.setCreatedBy("Admin");

        assertThrows(CombinedValidationException.class, () -> service.saveOrUpdate(dto));
    }

    // ✅ isInsuranceRequired Required
    @Test
    void testValidation_IsInsuranceRequiredMissingError() {
        EmployeeFamilyDetailsDTO dto = new EmployeeFamilyDetailsDTO();
        dto.setEmpBasicDetailId(1L);
        dto.setName("John");
        dto.setRelation("Brother");
        dto.setIsInsuranceRequired(""); // Will fail
        dto.setDob(LocalDateTime.of(2000,1,1,0,0));
        dto.setCreatedBy("Admin");

        assertThrows(CombinedValidationException.class, () -> service.saveOrUpdate(dto));
    }

    // ✅ DOB is in the future
    @Test
    void testValidation_DOBFutureError() {
        EmployeeFamilyDetailsDTO dto = new EmployeeFamilyDetailsDTO();
        dto.setEmpBasicDetailId(1L);
        dto.setName("John");
        dto.setRelation("Brother");
        dto.setIsInsuranceRequired("Yes");
        dto.setDob(LocalDateTime.now().plusDays(1)); // Will fail
        dto.setCreatedBy("Admin");

        assertThrows(CombinedValidationException.class, () -> service.saveOrUpdate(dto));
    }

    // ✅ CreatedBy required error
    @Test
    void testValidation_CreatedByMissingError() {
        EmployeeFamilyDetailsDTO dto = new EmployeeFamilyDetailsDTO();
        dto.setEmpBasicDetailId(1L);
        dto.setName("John");
        dto.setRelation("Brother");
        dto.setIsInsuranceRequired("Yes");
        dto.setDob(LocalDateTime.of(2000,1,1,0,0));

        assertThrows(CombinedValidationException.class, () -> service.saveOrUpdate(dto));
    }

    // ✅ CreatedBy Pattern Error
    @Test
    void testValidation_CreatedByPatternError() {
        EmployeeFamilyDetailsDTO dto = new EmployeeFamilyDetailsDTO();
        dto.setEmpBasicDetailId(1L);
        dto.setName("John");
        dto.setRelation("Brother");
        dto.setIsInsuranceRequired("Yes");
        dto.setDob(LocalDateTime.of(2000,1,1,0,0));
        dto.setCreatedBy("123Invalid");

        assertThrows(CombinedValidationException.class, () -> service.saveOrUpdate(dto));
    }

    // ✅ ModifiedBy Should Not Be Provided (Create Scenario)
    @Test
    void testValidation_CannotProvideModifiedByOnCreateError() {
        EmployeeFamilyDetailsDTO dto = new EmployeeFamilyDetailsDTO();
        dto.setEmpBasicDetailId(1L);
        dto.setName("John");
        dto.setRelation("Brother");
        dto.setIsInsuranceRequired("Yes");
        dto.setDob(LocalDateTime.of(2000,1,1,0,0));
        dto.setCreatedBy("Admin");
        dto.setModifiedBy("Editor"); // Will fail

        assertThrows(CombinedValidationException.class, () -> service.saveOrUpdate(dto));
    }

    // ✅ CreatedBy Should Not Be Provided (Update Scenario)
    @Test
    void testValidation_CannotProvideCreatedByOnUpdateError() {
        EmployeeFamilyDetailsDTO dto = new EmployeeFamilyDetailsDTO();
        dto.setEmpFamilyDetailId(1L);
        dto.setEmpBasicDetailId(1L);
        dto.setName("John");
        dto.setRelation("Brother");
        dto.setIsInsuranceRequired("Yes");
        dto.setDob(LocalDateTime.of(2000,1,1,0,0));
        dto.setCreatedBy("Admin"); // Will fail
        dto.setModifiedBy("Editor");

        assertThrows(CombinedValidationException.class, () -> service.saveOrUpdate(dto));
    }

    // ✅ CreatedDate Must Not Be Provided
    @Test
    void testValidation_CannotProvideCreatedDateError() {
        EmployeeFamilyDetailsDTO dto = new EmployeeFamilyDetailsDTO();
        dto.setEmpBasicDetailId(1L);
        dto.setName("John");
        dto.setRelation("Brother");
        dto.setIsInsuranceRequired("Yes");
        dto.setDob(LocalDateTime.of(2000,1,1,0,0));
        dto.setCreatedBy("Admin");
        dto.setCreatedDate(LocalDateTime.now());

        assertThrows(CombinedValidationException.class, () -> service.saveOrUpdate(dto));
    }

    // ✅ ModifiedDate Must Not Be Provided
    @Test
    void testValidation_CannotProvideModifiedDateError() {
        EmployeeFamilyDetailsDTO dto = new EmployeeFamilyDetailsDTO();
        dto.setEmpFamilyDetailId(1L);
        dto.setEmpBasicDetailId(1L);
        dto.setName("John");
        dto.setRelation("Brother");
        dto.setIsInsuranceRequired("Yes");
        dto.setDob(LocalDateTime.of(2000,1,1,0,0));
        dto.setModifiedBy("Editor");
        dto.setModifiedDate(LocalDateTime.now());

        assertThrows(CombinedValidationException.class, () -> service.saveOrUpdate(dto));
    }

    
    @Test
    void testValidation_MissingModifiedByError() {
        EmployeeFamilyDetailsDTO dto = new EmployeeFamilyDetailsDTO();
        dto.setEmpFamilyDetailId(1L); // Triggers UPDATE branch
        dto.setEmpBasicDetailId(1L);
        dto.setName("John");
        dto.setRelation("Brother");
        dto.setIsInsuranceRequired("Yes");
        dto.setDob(LocalDateTime.of(2000, 1, 1, 0, 0));
        dto.setModifiedBy(""); // Will fail ("modifiedBy" required)

        assertThrows(CombinedValidationException.class, 
            () -> service.saveOrUpdate(dto),
            "Expected CombinedValidationException for missing modifiedBy");
    }

    @Test
    void testValidation_InvalidModifiedByPattern_Error() {
        EmployeeFamilyDetailsDTO dto = new EmployeeFamilyDetailsDTO();
        dto.setEmpFamilyDetailId(1L);           // Trigger the UPDATE branch
        dto.setEmpBasicDetailId(1L);
        dto.setName("John");                    // Valid Name
        dto.setRelation("Brother");            // Valid Relation
        dto.setIsInsuranceRequired("Yes");     // Valid
        dto.setDob(LocalDateTime.of(2000, 1, 1, 0, 0)); // Valid DOB
        dto.setModifiedBy("123Invalid");       // Will FAIL pattern for modifiedBy

        CombinedValidationException thrown = assertThrows(CombinedValidationException.class,
                () -> service.saveOrUpdate(dto),
                "Expected CombinedValidationException due to invalid modifiedBy pattern");

        assertTrue(thrown.getErrors().containsKey("modifiedBy"));
        assertEquals("ModifiedBy must start with a letter and contain only letters, spaces, or dots",
                thrown.getErrors().get("modifiedBy"));
    }
    
    @Test
    void testSaveOrUpdate_Update_FamilyDetailNotFound() {
        // Arrange
        EmployeeFamilyDetailsDTO dto = new EmployeeFamilyDetailsDTO();
        dto.setEmpFamilyDetailId(99L);
        dto.setEmpBasicDetailId(1L);
        dto.setName("John");
        dto.setRelation("Brother");
        dto.setIsInsuranceRequired("Yes");
        dto.setDob(LocalDateTime.of(2000, 1, 1, 0, 0));
        dto.setModifiedBy("John");

        EmployeeBasicDetailsEntity emp = new EmployeeBasicDetailsEntity();
        when(basicRepo.findById(1L)).thenReturn(Optional.of(emp));

        when(repository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, 
                () -> service.saveOrUpdate(dto),
                "Expected ResourceNotFoundException when family detail not found");
        assertEquals("Family detail not found with ID 99", ex.getMessage());

        // Verify the repository call
        verify(repository).findById(99L);
    }



    @Test
    void testSearch_Found() {
        EmployeeFamilyDetails entity = new EmployeeFamilyDetails();
        entity.setName("John");
        when(repository.searchByNameAndRelation("John", "Brother"))
                .thenReturn(List.of(entity));

        List<EmployeeFamilyDetailsDTO> results = service.search("John", "Brother");
        assertEquals(1, results.size());
    }
}
