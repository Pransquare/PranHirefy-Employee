
//package com.pranhirefy.employee.service.impl;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Objects;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.pranhirefy.employee.dto.EmployeeBankDetailsDto;
//import com.pranhirefy.employee.entity.EmployeeBankDetailsEntity;
//import com.pranhirefy.employee.exception.AccountTypeNotFoundException;
//import com.pranhirefy.employee.exception.EmployeeBankDetailsNotFound;
////import com.pranhirefy.employee.exception.InvalidAccountTypeSearchException;
//import com.pranhirefy.employee.exception.InvalidEmployeeBankDetailException;
//import com.pranhirefy.employee.exception.InvalidEmployeeBankDetailsException;
//import com.pranhirefy.employee.exception.NoBankFoundException;
//import com.pranhirefy.employee.mapper.EmployeeBankDetailsMapper;
//import com.pranhirefy.employee.repository.EmployeeBankDetailsRepository;
//import com.pranhirefy.employee.repository.EmployeeBasicDetailsRepository;
//import com.pranhirefy.employee.service.EmployeeBankDetailsService;
//
//@Service
//public class EmployeeBankDetailsServiceImpl implements EmployeeBankDetailsService {
//	@Autowired
//    private  EmployeeBankDetailsRepository bankRepository;
//	@Autowired
//	private EmployeeBasicDetailsRepository employeeBasicDetailsRepository;
//
//	 @Autowired
//	    private EmployeeBankDetailsMapper mapper;
//   
//
//    @Override
//    public EmployeeBankDetailsDto createEmployeeBank(EmployeeBankDetailsDto dto) {
//    	 Map <String,String> errors = new HashMap<>();
//    	 validateEmployeeBankDetailsDto(dto, errors, true);
//    	 if (!errors.isEmpty()) {
//    	        throw new InvalidEmployeeBankDetailsException("Validation failed", errors);
//    	    }
//    	 
//    	    Date now = new Date();
//    	    dto.setCreatedDate(now);
////    	    dto.setBankAccountNo(dto.getBankAccountNo()); // Set trimmed and normalized value
//
//    	    dto.setModifiedDate(now);
//    	    dto.setModifiedBy(dto.getCreatedBy());
//    	    if (dto.getDeleted() == null || dto.getDeleted().trim().isEmpty()) {
//    	        dto.setDeleted("No");
//    	    }
//        EmployeeBankDetailsEntity entity = mapper.toEntity(dto);
//        EmployeeBankDetailsEntity savedEntity = bankRepository.save(entity);
//        return mapper.toDto(savedEntity);
//    }
//
////    @Override
////    public List<EmployeeBankDetailsDto> getAllEmployeeBank() {
////        List<EmployeeBankDetailsEntity> entities = bankRepository.findAll();
////        return entities.stream()
////                .map(mapper::toDto)
////                .collect(Collectors.toList());
////    }
//    // new
//    @Override
//    public List<EmployeeBankDetailsDto> getAllEmployeeBank() {
//        List<EmployeeBankDetailsEntity> entities = bankRepository.findAllByOrderByEmpBankDetailIdDesc();
//        return entities.stream()
//                .map(mapper::toDto)
//                .collect(Collectors.toList());
//    }
//
//    
////
////    @Override
////    public EmployeeBankDetailsDto getByEmployeeBankDetails(Long id) {
////        EmployeeBankDetailsEntity entity = bankRepository.findById(id)
////                .orElseThrow(() -> new EmployeeBankDetailsNotFound("Employee bank details with ID " + id + " not found"));
////        return mapper.toDto(entity);
////    }
//    @Override
//    public EmployeeBankDetailsDto getByEmployeeBankDetails(Long id) {
//        if (id == null || id <= 0) {
//            throw new InvalidEmployeeBankDetailException("Invalid ID: must be greater than 0.");
//        }
//
//        EmployeeBankDetailsEntity entity = bankRepository
//            .findById(id)
//            .orElseThrow(() -> new NoBankFoundException("Employee bank details with ID " + id + " not found"));
//
//        if ("Yes".equalsIgnoreCase(entity.getDeleted())) {
//            throw new NoBankFoundException("Employee bank details with ID " + id + " is already deleted.");
//        }
//
//        return mapper.toDto(entity);
//    }
//
//
//    @Override
//    public EmployeeBankDetailsDto updateEmployeeBankDetails(Long id, EmployeeBankDetailsDto dto) {
//
//        if (id == null || id <= 0) {
//            throw new InvalidEmployeeBankDetailException("Invalid ID: must be greater than 0.");
//        }
//
//        // Important: Set ID in DTO for validation comparison
//        dto.setEmpBankDetailId(id);
//
//        Map<String, String> errors = new HashMap<>();
//        validateEmployeeBankDetailsDto(dto, errors, false);  // isCreate = false
//
//        if (!errors.isEmpty()) {
//            throw new InvalidEmployeeBankDetailsException("Validation failed", errors);
//        }
//
//        EmployeeBankDetailsEntity existingEntity = bankRepository.findById(id)
//                .orElseThrow(() -> new NoBankFoundException("Employee bank details with ID " + id + " not found"));
//
//        existingEntity.setBankIfsc(dto.getBankIfsc());
//        existingEntity.setAccountType(dto.getAccountType());
//        existingEntity.setBankAccountNo(dto.getBankAccountNo());
//        existingEntity.setModifiedBy(existingEntity.getCreatedBy()); // Or set from session if needed
//        existingEntity.setModifiedDate(new Date());
//
//        EmployeeBankDetailsEntity updatedEntity = bankRepository.save(existingEntity);
//        return mapper.toDto(updatedEntity);
//    }
//
//
//
//
//
////    @Override
////    public void deleteByEmployeeBankDetails(Long id) {
////        EmployeeBankDetailsEntity entity = bankRepository.findById(id)
////            .orElseThrow(() -> new EmployeeBankDetailsNotFound("Employee bank details with ID " + id + " not found"));
////
////        entity.softDelete(); 
////        bankRepository.save(entity); 
////    }
//    
//    
//    @Override
//    public void deleteByEmployeeBankDetails(Long id) {
//        if (id == null || id <= 0) {
//            throw new InvalidEmployeeBankDetailException("Invalid ID: must be greater than 0.");
//        }
//
//        EmployeeBankDetailsEntity entity = bankRepository.findById(id)
//            .orElseThrow(() -> new  NoBankFoundException ("Employee bank details with ID " + id + " not found"));
//        if ("yes".equalsIgnoreCase(entity.getDeleted())) {
//            throw new NoBankFoundException("Bank detail with ID " + id + " is already deleted.");
//        }
//
//        entity.softDelete();
//        bankRepository.save(entity);
//    }
//
//     
//    
//    public void validateEmployeeBankDetailsDto(EmployeeBankDetailsDto dto, Map<String, String> errors, boolean isCreate) {
//
//        if (dto == null) {
//            errors.put("body", "Request body is required.");
//            return;
//        }
//
//        // === Basic Validations ===
//        // IFSC Code
//        if (dto.getBankIfsc() == null || dto.getBankIfsc().trim().isEmpty()) {
//            errors.put("bankIfsc", "Bank IFSC is required");
//        } else if (!dto.getBankIfsc().matches("^[A-Z]{4}0[A-Z0-9]{6}$")) {
//            errors.put("bankIfsc", "Invalid IFSC format (e.g., ABCD0123456).");
//        }
//
//        // Account Type
//        if (dto.getAccountType() == null || dto.getAccountType().trim().isEmpty()) {
//            errors.put("accountType", "Account type is required");
//        } else if (!dto.getAccountType().matches("(?i)^(savings|current|salary)$")) {
//            errors.put("accountType", "Account Type must be 'Savings', 'Current', or 'Salary'.");
//        }
//
//        // Account Number
//        if (dto.getBankAccountNo() == null || dto.getBankAccountNo().trim().isEmpty()) {
//            errors.put("bankAccountNo", "Bank account number is required.");
//        } else {
//            String acc = dto.getBankAccountNo().trim();
//            if (acc.contains(" ")) {
//                errors.put("bankAccountNo", "Bank account number must not contain spaces.");
//            } else if (!acc.matches("^\\d{9,18}$")) {
//                errors.put("bankAccountNo", "Bank account number must be 9 to 18 digits.");
//            } else if (isCreate && bankRepository.existsByBankAccountNoAndDeletedIgnoreCase(acc, "No")) {
//                errors.put("bankAccountNo", "Bank account number already exists.");
//            } else {
//                dto.setBankAccountNo(acc); // Cleaned value
//            }
//        }
//
//        // createdBy
//        if (isCreate) {
//            if (dto.getCreatedBy() == null || dto.getCreatedBy().trim().isEmpty()) {
//                errors.put("createdBy", "CreatedBy is required.");
//            } else if (!dto.getCreatedBy().matches("^[A-Za-z][A-Za-z. ]*$")) {
//                errors.put("createdBy", "CreatedBy should start with a letter and contain only letters, dots, and spaces.");
//            }
//        }
//
//        // ModifiedBy on update
//        if (!isCreate && dto.getModifiedBy() != null && !dto.getModifiedBy().trim().isEmpty()) {
//            if (!dto.getModifiedBy().matches("^[A-Za-z][A-Za-z. ]*$")) {
//                errors.put("modifiedBy", "ModifiedBy should start with a letter and contain only letters, dots, and spaces.");
//            }
//        }
//
//        // FK check
//        if (dto.getEmployeeBasicDetailId() == null) {
//            errors.put("employeeBasicDetailId", "EmployeeBasicDetailId is required.");
//        } else if (!employeeBasicDetailsRepository.existsById(dto.getEmployeeBasicDetailId())) {
//            errors.put("employeeBasicDetailId", "No employee exists with ID " + dto.getEmployeeBasicDetailId());
//        }
//
//        // Creation-only rules
//        if (isCreate) {
//            if (dto.getModifiedBy() != null) {
//                errors.put("modifiedBy", "Do not provide ModifiedBy during creation.");
//            }
//            if (dto.getModifiedDate() != null) {
//                errors.put("modifiedDate", "Do not provide ModifiedDate during creation.");
//            }
//            if (dto.getCreatedDate() != null) {
//                errors.put("createdDate", "Do not provide CreatedDate during creation.");
//            }
//            if (dto.getDeleted() != null && !dto.getDeleted().trim().equalsIgnoreCase("No")) {
//                errors.put("deleted", "Only 'No' is allowed for 'deleted' during creation.");
//            }
//            if (dto.getEmpBankDetailId() != null) {
//                throw new InvalidEmployeeBankDetailException("ID must not be set while creating new bank detail.");
//            }
//        }
//
//        // Update-only rules
//        if (!isCreate && dto.getEmpBankDetailId() != null) {
//            Optional<EmployeeBankDetailsEntity> opt = bankRepository.findById(dto.getEmpBankDetailId());
//            if (opt.isPresent()) {
//                EmployeeBankDetailsEntity existing = opt.get();
//
//                // 🛑 Block update if soft-deleted
//                if ("yes".equalsIgnoreCase(existing.getDeleted())) {
//                    errors.put("deleted", "Cannot update a soft-deleted bank detail.");
//                    return; // no need to continue
//                }
//
//                // 🛑 Immutable field changes
//                if (dto.getCreatedBy() != null && !dto.getCreatedBy().trim().equals(existing.getCreatedBy())) {
//                    errors.put("createdBy", "createdBy cannot be modified once set.");
//                }
//
//                if (dto.getCreatedDate() != null && !dto.getCreatedDate().equals(existing.getCreatedDate())) {
//                    errors.put("createdDate", "createdDate cannot be modified once set.");
//                }
//
//                if (dto.getDeleted() != null && !dto.getDeleted().equalsIgnoreCase(existing.getDeleted())) {
//                    errors.put("deleted", "deleted field cannot be modified.");
//                }
//
//                if (dto.getModifiedBy() != null) {
//                    errors.put("modifiedBy", "modifiedBy is set automatically. Do not provide it in the request.");
//                }
//
//                if (dto.getModifiedDate() != null) {
//                    errors.put("modifiedDate", "modifiedDate is set automatically. Do not provide it in the request.");
//                }
//
//                // 🛑 Detect no actual changes
//                boolean noChangeInAccountNo = dto.getBankAccountNo().trim().equals(existing.getBankAccountNo().trim());
//                boolean noChangeInIfsc = dto.getBankIfsc().trim().equals(existing.getBankIfsc().trim());
//                boolean noChangeInType = dto.getAccountType().trim().equalsIgnoreCase(existing.getAccountType().trim());
//
//                if (noChangeInAccountNo && noChangeInIfsc && noChangeInType) {
//                    errors.put("noChanges", "No changes detected. At least one field (bankAccountNo, bankIfsc, accountType) must be modified.");
//                }
//
//            } else {
//                errors.put("empBankDetailId", "Bank detail not found for update.");
//            }
//        }
//    }
//
// 
// 
//
//
//    
//
//}

//
package com.pranhirefy.employee.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pranhirefy.employee.dto.EmployeeBankDetailsDto;
import com.pranhirefy.employee.entity.EmployeeBankDetailsEntity;
import com.pranhirefy.employee.exception.AccountTypeNotFoundException;
import com.pranhirefy.employee.exception.EmployeeBankDetailsNotFound;
import com.pranhirefy.employee.exception.InvalidEmployeeBankDetailException;
import com.pranhirefy.employee.exception.InvalidEmployeeBankDetailsException;
import com.pranhirefy.employee.exception.NoBankFoundException;
import com.pranhirefy.employee.mapper.EmployeeBankDetailsMapper;
import com.pranhirefy.employee.repository.EmployeeBankDetailsRepository;
import com.pranhirefy.employee.repository.EmployeeBasicDetailsRepository;
import com.pranhirefy.employee.service.EmployeeBankDetailsService;

@Service
public class EmployeeBankDetailsServiceImpl implements EmployeeBankDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeBankDetailsServiceImpl.class);

    @Autowired
    private EmployeeBankDetailsRepository bankRepository;

    @Autowired
    private EmployeeBasicDetailsRepository employeeBasicDetailsRepository;

    @Autowired
    private EmployeeBankDetailsMapper mapper;

    @Override
    public EmployeeBankDetailsDto createEmployeeBank(EmployeeBankDetailsDto dto) {
        logger.info("Creating employee bank detail for employeeId: {}", dto.getEmployeeBasicDetailId());
        Map<String, String> errors = new HashMap<>();
        validateEmployeeBankDetailsDto(dto, errors, true);
        if (!errors.isEmpty()) {
            logger.error("Validation errors during createEmployeeBank: {}", errors);
            throw new InvalidEmployeeBankDetailsException("Validation failed", errors);
        }

        Date now = new Date();
        dto.setCreatedDate(now);
        dto.setModifiedDate(now);
        dto.setModifiedBy(dto.getCreatedBy());

        if (dto.getDeleted() == null || dto.getDeleted().trim().isEmpty()) {
            dto.setDeleted("No");
        }

        EmployeeBankDetailsEntity entity = mapper.toEntity(dto);
        EmployeeBankDetailsEntity savedEntity = bankRepository.save(entity);
        logger.info("Employee bank detail created with ID: {}", savedEntity.getEmpBankDetailId());
        return mapper.toDto(savedEntity);
    }

    @Override
    public List<EmployeeBankDetailsDto> getAllEmployeeBank() {
        logger.info("Fetching all employee bank details in descending order");
        List<EmployeeBankDetailsEntity> entities = bankRepository.findAllByOrderByEmpBankDetailIdDesc();
        return entities.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeBankDetailsDto getByEmployeeBankDetails(Long id) {
        logger.info("Fetching bank detail for ID: {}", id);
        if (id == null || id <= 0) {
            logger.error("Invalid ID provided: {}", id);
            throw new InvalidEmployeeBankDetailException("Invalid ID: must be greater than 0.");
        }

        EmployeeBankDetailsEntity entity = bankRepository
                .findById(id)
                .orElseThrow(() -> {
                    logger.error("Bank detail not found for ID: {}", id);
                    return new NoBankFoundException("Employee bank details with ID " + id + " not found");
                });

        if ("Yes".equalsIgnoreCase(entity.getDeleted())) {
            logger.warn("Attempted to access deleted bank detail with ID: {}", id);
            throw new NoBankFoundException("Employee bank details with ID " + id + " is already deleted.");
        }

        return mapper.toDto(entity);
    }

//    @Override
//    public EmployeeBankDetailsDto updateEmployeeBankDetails(Long id, EmployeeBankDetailsDto dto) {
//        logger.info("Updating bank detail with ID: {}", id);
//
//        if (id == null || id <= 0) {
//            logger.error("Invalid ID provided for update: {}", id);
//            throw new InvalidEmployeeBankDetailException("Invalid ID: must be greater than 0.");
//        }
//        
//      
//
//        dto.setEmpBankDetailId(id);
//
//        Map<String, String> errors = new HashMap<>();
//        validateEmployeeBankDetailsDto(dto, errors, false);
//
//        if (!errors.isEmpty()) {
//            logger.error("Validation errors during updateEmployeeBankDetails: {}", errors);
//            throw new InvalidEmployeeBankDetailsException("Validation failed", errors);
//        }
//
//        EmployeeBankDetailsEntity existingEntity = bankRepository.findById(id)
//                .orElseThrow(() -> {
//                    logger.error("Bank detail not found for update with ID: {}", id);
//                    return new NoBankFoundException("Employee bank details with ID " + id + " not found");
//                });
//
//        existingEntity.setBankIfsc(dto.getBankIfsc());
//        existingEntity.setAccountType(dto.getAccountType());
//        existingEntity.setBankAccountNo(dto.getBankAccountNo());
//        existingEntity.setModifiedBy(existingEntity.getCreatedBy()); // Or set from session if needed
//        existingEntity.setModifiedDate(new Date());
//
//        EmployeeBankDetailsEntity updatedEntity = bankRepository.save(existingEntity);
//        logger.info("Bank detail updated for ID: {}", updatedEntity.getEmpBankDetailId());
//        return mapper.toDto(updatedEntity);
//    }

    @Override
    public EmployeeBankDetailsDto updateEmployeeBankDetails(Long id, EmployeeBankDetailsDto dto) {
        logger.info("Updating bank detail with ID: {}", id);

        if (id == null || id <= 0) {
            logger.error("Invalid ID provided for update: {}", id);
            throw new InvalidEmployeeBankDetailException("Invalid ID: must be greater than 0.");
        }

        dto.setEmpBankDetailId(id);

        Map<String, String> errors = new HashMap<>();
        validateEmployeeBankDetailsDto(dto, errors, false);

        if (!errors.isEmpty()) {
            logger.error("Validation errors during updateEmployeeBankDetails: {}", errors);
            throw new InvalidEmployeeBankDetailsException("Validation failed", errors);
        }

        EmployeeBankDetailsEntity existingEntity = bankRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Bank detail not found for update with ID: {}", id);
                    return new NoBankFoundException("Employee bank details with ID " + id + " not found");
                });

        existingEntity.setBankIfsc(dto.getBankIfsc());
        existingEntity.setAccountType(dto.getAccountType());
        existingEntity.setBankAccountNo(dto.getBankAccountNo());
        existingEntity.setModifiedBy(existingEntity.getCreatedBy());
        existingEntity.setModifiedDate(new Date());

        EmployeeBankDetailsEntity updatedEntity = bankRepository.save(existingEntity);
        logger.info("Bank detail updated for ID: {}", updatedEntity.getEmpBankDetailId());
        return mapper.toDto(updatedEntity);
    }

    @Override
    public void deleteByEmployeeBankDetails(Long id) {
        logger.info("Deleting (soft) bank detail for ID: {}", id);

        if (id == null || id <= 0) {
            logger.error("Invalid ID provided for delete: {}", id);
            throw new InvalidEmployeeBankDetailException("Invalid ID: must be greater than 0.");
        }

        EmployeeBankDetailsEntity entity = bankRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Bank detail not found for delete with ID: {}", id);
                    return new NoBankFoundException("Employee bank details with ID " + id + " not found");
                });

        if ("yes".equalsIgnoreCase(entity.getDeleted())) {
            logger.warn("Bank detail already deleted with ID: {}", id);
            throw new NoBankFoundException("Bank detail with ID " + id + " is already deleted.");
        }

        entity.softDelete();
        bankRepository.save(entity);
        logger.info("Bank detail soft deleted with ID: {}", id);
    }

    public void validateEmployeeBankDetailsDto(EmployeeBankDetailsDto dto, Map<String, String> errors, boolean isCreate) {
        logger.debug("Validating EmployeeBankDetailsDto: {}, isCreate: {}", dto, isCreate);

        if (dto == null) {
            errors.put("body", "Request body is required.");
            logger.warn("Validation failed: request body is null");
            return;
        }

        // === Basic Validations ===
        if (dto.getBankIfsc() == null || dto.getBankIfsc().trim().isEmpty()) {
            errors.put("bankIfsc", "Bank IFSC is required");
        } else if (!dto.getBankIfsc().matches("^[A-Z]{4}0[A-Z0-9]{6}$")) {
            errors.put("bankIfsc", "Invalid IFSC format (e.g., ABCD0123456).");
        }

        if (dto.getAccountType() == null || dto.getAccountType().trim().isEmpty()) {
            errors.put("accountType", "Account type is required");
        } else if (!dto.getAccountType().matches("(?i)^(savings|current|salary)$")) {
            errors.put("accountType", "Account Type must be 'Savings', 'Current', or 'Salary'.");
        }

        if (dto.getBankAccountNo() == null || dto.getBankAccountNo().trim().isEmpty()) {
            errors.put("bankAccountNo", "Bank account number is required.");
        } else {
            String acc = dto.getBankAccountNo().trim();
            if (acc.contains(" ")) {
                errors.put("bankAccountNo", "Bank account number must not contain spaces.");
            } else if (!acc.matches("^\\d{9,18}$")) {
                errors.put("bankAccountNo", "Bank account number must be 9 to 18 digits.");
            } else if (isCreate && bankRepository.existsByBankAccountNoAndDeletedIgnoreCase(acc, "No")) {
                errors.put("bankAccountNo", "Bank account number already exists.");
            } else {
                dto.setBankAccountNo(acc);
            }
        }
        
        
//        if (dto.getBankAccountNo() == null || dto.getBankAccountNo().trim().isEmpty()) {
//            errors.put("bankAccountNo", "Bank account number is required.");
//        } else {
//            String acc = dto.getBankAccountNo().trim();
//
//            // Check for invalid characters or whitespace
//            if (acc.contains(" ")) {
//                errors.put("bankAccountNo", "Bank account number must not contain spaces.");
//            }
//            // Allow digits only, 9 to 18 digits (including those starting with 0)
//            else if (!acc.matches("^[0-9]{9,18}$")) {
//                errors.put("bankAccountNo", "Bank account number must be 9 to 18 digits and can start with 0.");
//            }
//            // Check for duplicate only during creation
//            else if (isCreate && bankRepository.existsByBankAccountNoAndDeletedIgnoreCase(acc, "No")) {
//                errors.put("bankAccountNo", "Bank account number already exists.");
//            }
//            else {
//                dto.setBankAccountNo(acc); // Cleaned and validated account number
//            }
//        }

        
        
        
        
        
        
        

        if (isCreate) {
            if (dto.getCreatedBy() == null || dto.getCreatedBy().trim().isEmpty()) {
                errors.put("createdBy", "CreatedBy is required.");
            } else if (!dto.getCreatedBy().matches("^[A-Za-z][A-Za-z. ]*$")) {
                errors.put("createdBy", "CreatedBy should start with a letter and contain only letters, dots, and spaces.");
            }
        }

        if (!isCreate && dto.getModifiedBy() != null && !dto.getModifiedBy().trim().isEmpty()) {
            if (!dto.getModifiedBy().matches("^[A-Za-z][A-Za-z. ]*$")) {
                errors.put("modifiedBy", "ModifiedBy should start with a letter and contain only letters, dots, and spaces.");
            }
        }

        if (dto.getEmployeeBasicDetailId() == null) {
            errors.put("employeeBasicDetailId", "EmployeeBasicDetailId is required.");
        } else if (!employeeBasicDetailsRepository.existsById(dto.getEmployeeBasicDetailId())) {
            errors.put("employeeBasicDetailId", "No employee exists with ID " + dto.getEmployeeBasicDetailId());
        }

        if (isCreate) {
            if (dto.getModifiedBy() != null) {
                errors.put("modifiedBy", "Do not provide ModifiedBy during creation.");
            }
            if (dto.getModifiedDate() != null) {
                errors.put("modifiedDate", "Do not provide ModifiedDate during creation.");
            }
            if (dto.getCreatedDate() != null) {
                errors.put("createdDate", "Do not provide CreatedDate during creation.");
            }
            if (dto.getDeleted() != null && !dto.getDeleted().trim().equalsIgnoreCase("No")) {
                errors.put("deleted", "Only 'No' is allowed for 'deleted' during creation.");
            }
            if (dto.getEmpBankDetailId() != null) {
                throw new InvalidEmployeeBankDetailException("ID must not be set while creating new bank detail.");
            }
        }

        if (!isCreate && dto.getEmpBankDetailId() != null) {
            Optional<EmployeeBankDetailsEntity> opt = bankRepository.findById(dto.getEmpBankDetailId());
            if (opt.isPresent()) {
                EmployeeBankDetailsEntity existing = opt.get();

                if ("yes".equalsIgnoreCase(existing.getDeleted())) {
                    errors.put("deleted", "Cannot update a soft-deleted bank detail.");
                    return;
                }

                if (dto.getCreatedBy() != null && !dto.getCreatedBy().trim().equals(existing.getCreatedBy())) {
                    errors.put("createdBy", "createdBy cannot be modified once set.");
                }

                if (dto.getCreatedDate() != null && !dto.getCreatedDate().equals(existing.getCreatedDate())) {
                    errors.put("createdDate", "createdDate cannot be modified once set.");
                }

                if (dto.getDeleted() != null && !dto.getDeleted().equalsIgnoreCase(existing.getDeleted())) {
                    errors.put("deleted", "deleted field cannot be modified.");
                }

                if (dto.getModifiedBy() != null) {
                    errors.put("modifiedBy", "modifiedBy is set automatically. Do not provide it in the request.");
                }

                if (dto.getModifiedDate() != null) {
                    errors.put("modifiedDate", "modifiedDate is set automatically. Do not provide it in the request.");
                }

                boolean noChangeInAccountNo = dto.getBankAccountNo().trim().equals(existing.getBankAccountNo().trim());
                boolean noChangeInIfsc = dto.getBankIfsc().trim().equals(existing.getBankIfsc().trim());
                boolean noChangeInType = dto.getAccountType().trim().equalsIgnoreCase(existing.getAccountType().trim());

                if (noChangeInAccountNo && noChangeInIfsc && noChangeInType) {
                    errors.put("noChanges", "No changes detected. At least one field (bankAccountNo, bankIfsc, accountType) must be modified.");
                }

            } 
//            else {
//                errors.put("empBankDetailId", "Bank detail not found for update.");
//            }
            else {
                // Let the service method handle not-found case
                return;
            }

        }
    }
}

