//package com.pranhirefy.employee.service.impl;
//
//import com.pranhirefy.employee.dto.EmployeeFamilyDetailsDTO;
//import com.pranhirefy.employee.entity.EmployeeBasicDetailsEntity;
//import com.pranhirefy.employee.entity.EmployeeFamilyDetails;
//import com.pranhirefy.employee.exception.*;
//import com.pranhirefy.employee.mapper.EmployeeFamilyDetailsMapper;
//import com.pranhirefy.employee.repository.EmployeeFamilyDetailsRepository;
//import com.pranhirefy.employee.repository.EmployeeBasicDetailsRepository;
//import com.pranhirefy.employee.service.EmployeeFamilyDetailsService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.*;
//import java.util.stream.Collectors;
//
//@Service
//public class EmployeeFamilyDetailsServiceImpl implements EmployeeFamilyDetailsService {
//
//    private static final Logger logger = LoggerFactory.getLogger(EmployeeFamilyDetailsServiceImpl.class);
//
//    @Autowired
//    private EmployeeFamilyDetailsRepository repository;
//
//    @Autowired
//    private EmployeeBasicDetailsRepository basicRepo;
//
//    @Override
//    public EmployeeFamilyDetailsDTO saveOrUpdate(EmployeeFamilyDetailsDTO dto) {
//        logger.info("Start saving/updating EmployeeFamilyDetails: {}", dto);
//
//        if (dto == null) {
//            logger.error("Request body is null");
//            throw new EmptyRequestBodyException("Request body is missing");
//        }
//
//        validateInput(dto);
//
//        EmployeeBasicDetailsEntity emp = basicRepo.findById(dto.getEmpBasicDetailId())
//                .orElseThrow(() -> {
//                    logger.warn("Employee not found with ID: {}", dto.getEmpBasicDetailId());
//                    return new ResourceNotFoundException("Employee not found with ID " + dto.getEmpBasicDetailId());
//                });
//
//        EmployeeFamilyDetails entity = EmployeeFamilyDetailsMapper.toEntity(dto);
//        entity.setEmpBasicDetail(emp);
//
//        LocalDateTime now = LocalDateTime.now();
//
//        if (entity.getEmpFamilyDetailId() == null) {
//            // CREATE
//            logger.debug("Creating new family detail record");
//            entity.setCreatedDate(now);
//            entity.setModifiedDate(now);
//            entity.setModifiedBy(dto.getCreatedBy()); // auto-set
//            entity.setIsDeleted("No");
//        } else {
//            // UPDATE
//            logger.debug("Updating existing family detail with ID: {}", entity.getEmpFamilyDetailId());
//            EmployeeFamilyDetails existing = repository.findById(entity.getEmpFamilyDetailId())
//                    .orElseThrow(() -> {
//                        logger.warn("Family detail not found with ID: {}", entity.getEmpFamilyDetailId());
//                        return new ResourceNotFoundException("Family detail not found with ID " + entity.getEmpFamilyDetailId());
//                    });
//
//            entity.setCreatedDate(existing.getCreatedDate());
//            entity.setCreatedBy(existing.getCreatedBy());
//            entity.setIsDeleted(existing.getIsDeleted());
//            entity.setModifiedDate(now);
//        }
//
//        EmployeeFamilyDetails savedEntity = repository.save(entity);
//        logger.info("Successfully saved EmployeeFamilyDetails with ID: {}", savedEntity.getEmpFamilyDetailId());
//
//        return EmployeeFamilyDetailsMapper.toDTO(savedEntity);
//    }
//
//    private void validateInput(EmployeeFamilyDetailsDTO dto) {
//        logger.debug("Validating EmployeeFamilyDetailsDTO input...");
//        Map<String, String> errors = new HashMap<>();
//        String namePattern = "^[A-Za-z][A-Za-z .]*$";
//        if (dto.getEmpFamilyDetailId() != null) {
//            boolean exists = repository.existsByEmpFamilyDetailIdAndIsDeleted(dto.getEmpFamilyDetailId(), "No");
//            if (exists) {
//                logger.warn("Family detail already exists with ID: {}", dto.getEmpFamilyDetailId());
//                throw new AlreadyExistsException("Family detail already exists with  " + dto.getEmpFamilyDetailId());
//            }
//        }
//
//
//        if (dto.getEmpBasicDetailId() == null)
//            errors.put("empBasicDetailId", "Employee ID is required");
//
//        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
//            errors.put("name", "Name is required");
//        } else if (!dto.getName().matches(namePattern)) {
//            errors.put("name", "Name must start with a letter and contain only letters, spaces, or dots");
//        }
//
//        if (dto.getRelation() == null || dto.getRelation().trim().isEmpty()) {
//            errors.put("relation", "Relation is required");
//        } else if (!dto.getRelation().matches(namePattern)) {
//            errors.put("relation", "Relation must start with a letter and contain only letters, spaces, or dots");
//        }
//
//        if (dto.getIsInsuranceRequired() == null || dto.getIsInsuranceRequired().trim().isEmpty()) {
//            errors.put("isInsuranceRequired", "Insurance requirement is required");
//        }
//
//        if (dto.getDob() == null || dto.getDob().isAfter(LocalDateTime.now())) {
//            errors.put("dob", "Date of Birth is required and must be in the past");
//        }
//
//        // Conditional validation based on create/update
//        if (dto.getEmpFamilyDetailId() == null) {
//            if (dto.getCreatedBy() == null || dto.getCreatedBy().trim().isEmpty()) {
//                errors.put("createdBy", "CreatedBy is required");
//            } else if (!dto.getCreatedBy().matches(namePattern)) {
//                errors.put("createdBy", "CreatedBy must start with a letter and contain only letters, spaces, or dots");
//            }
//
//            if (dto.getModifiedBy() != null) {
//                errors.put("modifiedBy", "ModifiedBy must not be provided — it is auto-set from createdBy during creation");
//            }
//        } else {
//            if (dto.getModifiedBy() == null || dto.getModifiedBy().trim().isEmpty()) {
//                errors.put("modifiedBy", "ModifiedBy is required");
//            } else if (!dto.getModifiedBy().matches(namePattern)) {
//                errors.put("modifiedBy", "ModifiedBy must start with a letter and contain only letters, spaces, or dots");
//            }
//
//            if (dto.getCreatedBy() != null) {
//                errors.put("createdBy", "CreatedBy must not be modified — it is set only during creation");
//            }
//        }
//
//        if (dto.getCreatedDate() != null)
//            errors.put("createdDate", "createdDate must not be provided — it is auto-generated");
//
//        if (dto.getModifiedDate() != null)
//            errors.put("modifiedDate", "modifiedDate must not be provided — it is auto-generated");
//
//        if (!errors.isEmpty()) {
//            logger.warn("Validation failed with errors: {}", errors);
//            throw new CombinedValidationException(errors);
//        }
//
//        logger.debug("Validation passed");
//    }
//
//    @Override
//    public List<EmployeeFamilyDetailsDTO> getAll() {
//        logger.info("Fetching all non-deleted EmployeeFamilyDetails records in descending order by ID");
//
//        List<EmployeeFamilyDetailsDTO> list = repository.findByIsDeletedOrderByEmpFamilyDetailIdDesc("No")
//                .stream()
//                .map(EmployeeFamilyDetailsMapper::toDTO)
//                .collect(Collectors.toList());
//
//        logger.debug("Fetched {} records", list.size());
//        return list;
//    }
//
//    @Override
//    public EmployeeFamilyDetailsDTO getById(Long id) {
//        logger.info("Fetching EmployeeFamilyDetails by ID: {}", id);
//        if (id == null || id <= 0) {
//            logger.error("Invalid ID provided: {}", id);
//            throw new BadRequestException("Invalid ID: " + id);
//        }
//
//        EmployeeFamilyDetails entity = repository.findByEmpFamilyDetailIdAndIsDeleted(id, "No")
//                .orElseThrow(() -> {
//                    logger.warn("Family detail not found for ID: {}", id);
//                    return new ResourceNotFoundException("Family detail not found with ID " + id);
//                });
//
//        return EmployeeFamilyDetailsMapper.toDTO(entity);
//    }
//
//    @Override
//    public void delete(Long id) {
//        logger.info("Soft deleting EmployeeFamilyDetails with ID: {}", id);
//        if (id == null || id <= 0) {
//            logger.error("Invalid ID for delete: {}", id);
//            throw new BadRequestException("Invalid ID: " + id);
//        }
//
//        EmployeeFamilyDetails entity = repository.findByEmpFamilyDetailIdAndIsDeleted(id, "No")
//                .orElseThrow(() -> {
//                    logger.warn("Cannot delete — record not found for ID: {}", id);
//                    return new ResourceNotFoundException("Family detail not found with ID " + id);
//                });
//
//        entity.setIsDeleted("Yes");
//        entity.setModifiedDate(LocalDateTime.now());
//        repository.save(entity);
//
//        logger.info("Successfully soft deleted EmployeeFamilyDetails with ID: {}", id);
//    }
//
//    @Override
//    public List<EmployeeFamilyDetailsDTO> search(String name, String relation) {
//        logger.info("Searching EmployeeFamilyDetails with name='{}', relation='{}'", name, relation);
//        List<EmployeeFamilyDetails> results = repository.searchByNameAndRelation(name, relation);
//        logger.debug("Found {} matching records", results.size());
//
//        return results.stream()
//                .map(EmployeeFamilyDetailsMapper::toDTO)
//                .collect(Collectors.toList());
//    }
//}
//

package com.pranhirefy.employee.service.impl;

import com.pranhirefy.employee.dto.EmployeeFamilyDetailsDTO;
import com.pranhirefy.employee.entity.EmployeeBasicDetailsEntity;
import com.pranhirefy.employee.entity.EmployeeFamilyDetails;
import com.pranhirefy.employee.exception.*;
import com.pranhirefy.employee.mapper.EmployeeFamilyDetailsMapper;
import com.pranhirefy.employee.repository.EmployeeFamilyDetailsRepository;
import com.pranhirefy.employee.repository.EmployeeBasicDetailsRepository;
import com.pranhirefy.employee.service.EmployeeFamilyDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeFamilyDetailsServiceImpl implements EmployeeFamilyDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeFamilyDetailsServiceImpl.class);

    @Autowired
    private EmployeeFamilyDetailsRepository repository;

    @Autowired
    private EmployeeBasicDetailsRepository basicRepo;

    @Override
    public EmployeeFamilyDetailsDTO saveOrUpdate(EmployeeFamilyDetailsDTO dto) {
        logger.info("Start saving/updating EmployeeFamilyDetails: {}", dto);

        if (dto == null) {
            logger.error("Request body is null");
            throw new EmptyRequestBodyException("Request body is missing");
        }

        validateInput(dto);

        EmployeeBasicDetailsEntity emp = basicRepo.findById(dto.getEmpBasicDetailId())
                .orElseThrow(() -> {
                    logger.warn("Employee not found with ID: {}", dto.getEmpBasicDetailId());
                    return new ResourceNotFoundException("Employee not found with ID " + dto.getEmpBasicDetailId());
                });

        EmployeeFamilyDetails entity = EmployeeFamilyDetailsMapper.toEntity(dto);
        entity.setEmpBasicDetail(emp);

        LocalDateTime now = LocalDateTime.now();

        if (entity.getEmpFamilyDetailId() == null) {
            // CREATE
            logger.debug("Creating new family detail record");
            entity.setCreatedDate(now);
            entity.setModifiedDate(now);
            entity.setModifiedBy(dto.getCreatedBy()); // auto-set
            entity.setIsDeleted("No");
        } else {
            // UPDATE
            logger.debug("Updating existing family detail with ID: {}", entity.getEmpFamilyDetailId());
            EmployeeFamilyDetails existing = repository.findById(entity.getEmpFamilyDetailId())
                    .orElseThrow(() -> {
                        logger.warn("Family detail not found with ID: {}", entity.getEmpFamilyDetailId());
                        return new ResourceNotFoundException("Family detail not found with ID " + entity.getEmpFamilyDetailId());
                    });

            entity.setCreatedDate(existing.getCreatedDate());
            entity.setCreatedBy(existing.getCreatedBy());
            entity.setIsDeleted(existing.getIsDeleted());
            entity.setModifiedDate(now);
        }

        EmployeeFamilyDetails savedEntity = repository.save(entity);
        logger.info("Successfully saved EmployeeFamilyDetails with ID: {}", savedEntity.getEmpFamilyDetailId());

        return EmployeeFamilyDetailsMapper.toDTO(savedEntity);
    }

    private void validateInput(EmployeeFamilyDetailsDTO dto) {
        logger.debug("Validating EmployeeFamilyDetailsDTO input...");
        Map<String, String> errors = new HashMap<>();
        String namePattern = "^[A-Za-z][A-Za-z .]*$";

        if (dto.getEmpBasicDetailId() == null)
            errors.put("empBasicDetailId", "Employee ID is required");

        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            errors.put("name", "Name is required");
        } else if (!dto.getName().matches(namePattern)) {
            errors.put("name", "Name must start with a letter and contain only letters");
        }

        if (dto.getRelation() == null || dto.getRelation().trim().isEmpty()) {
            errors.put("relation", "Relation is required");
        } else if (!dto.getRelation().matches(namePattern)) {
            errors.put("relation", "Relation must start with a letter and contain only letters, spaces, or dots");
        }

        if (dto.getIsInsuranceRequired() == null || dto.getIsInsuranceRequired().trim().isEmpty()) {
            errors.put("isInsuranceRequired", "Insurance requirement is required");
        }

        if (dto.getDob() == null || dto.getDob().isAfter(LocalDateTime.now())) {
            errors.put("dob", "Date of Birth is required and must be in the past");
        }

        if (dto.getEmpFamilyDetailId() == null) {
            // CREATE
            if (dto.getCreatedBy() == null || dto.getCreatedBy().trim().isEmpty()) {
                errors.put("createdBy", "CreatedBy is required");
            } else if (!dto.getCreatedBy().matches(namePattern)) {
                errors.put("createdBy", "CreatedBy must start with a letter and contain only letters, spaces, or dots");
            }

            if (dto.getModifiedBy() != null) {
                errors.put("modifiedBy", "ModifiedBy must not be provided — it is auto-set from createdBy during creation");
            }
        } else {
            // UPDATE
            if (dto.getModifiedBy() == null || dto.getModifiedBy().trim().isEmpty()) {
                errors.put("modifiedBy", "ModifiedBy is required");
            } else if (!dto.getModifiedBy().matches(namePattern)) {
                errors.put("modifiedBy", "ModifiedBy must start with a letter and contain only letters, spaces, or dots");
            }

            if (dto.getCreatedBy() != null) {
                errors.put("createdBy", "CreatedBy must not be modified — it is set only during creation");
            }
        }

        if (dto.getCreatedDate() != null)
            errors.put("createdDate", "createdDate must not be provided — it is auto-generated");

        if (dto.getModifiedDate() != null)
            errors.put("modifiedDate", "modifiedDate must not be provided — it is auto-generated");

        if (!errors.isEmpty()) {
            logger.warn("Validation failed with errors: {}", errors);
            throw new CombinedValidationException(errors);
        }

        logger.debug("Validation passed");
    }

    @Override
    public List<EmployeeFamilyDetailsDTO> getAll() {
        logger.info("Fetching all non-deleted EmployeeFamilyDetails records in descending order by ID");

        List<EmployeeFamilyDetailsDTO> list = repository.findByIsDeletedOrderByEmpFamilyDetailIdDesc("No")
                .stream()
                .map(EmployeeFamilyDetailsMapper::toDTO)
                .collect(Collectors.toList());

        logger.debug("Fetched {} records", list.size());
        return list;
    }

    @Override
    public EmployeeFamilyDetailsDTO getById(Long id) {
        logger.info("Fetching EmployeeFamilyDetails by ID: {}", id);
        if (id == null || id <= 0) {
            logger.error("Invalid ID provided: {}", id);
            throw new BadRequestException("Invalid ID: " + id);
        }

        EmployeeFamilyDetails entity = repository.findByEmpFamilyDetailIdAndIsDeleted(id, "No")
                .orElseThrow(() -> {
                    logger.warn("Family detail not found for ID: {}", id);
                    return new ResourceNotFoundException("Family detail not found with ID " + id);
                });

        return EmployeeFamilyDetailsMapper.toDTO(entity);
    }

    @Override
    public void delete(Long id) {
        logger.info("Soft deleting EmployeeFamilyDetails with ID: {}", id);
        if (id == null || id <= 0) {
            logger.error("Invalid ID for delete: {}", id);
            throw new BadRequestException("Invalid ID: " + id);
        }

        EmployeeFamilyDetails entity = repository.findByEmpFamilyDetailIdAndIsDeleted(id, "No")
                .orElseThrow(() -> {
                    logger.warn("Cannot delete — record not found for ID: {}", id);
                    return new ResourceNotFoundException("Family detail not found with ID " + id);
                });

        entity.setIsDeleted("Yes");
        entity.setModifiedDate(LocalDateTime.now());
        repository.save(entity);

        logger.info("Successfully soft deleted EmployeeFamilyDetails with ID: {}", id);
    }

    @Override
    public List<EmployeeFamilyDetailsDTO> search(String name, String relation) {
        logger.info("Searching EmployeeFamilyDetails with name='{}', relation='{}'", name, relation);
        List<EmployeeFamilyDetails> results = repository.searchByNameAndRelation(name, relation);
        logger.debug("Found {} matching records", results.size());

        return results.stream()
                .map(EmployeeFamilyDetailsMapper::toDTO)
                .collect(Collectors.toList());
    }
}

