//
//package com.pranhirefy.employee.controller;
//
//import com.fasterxml.jackson.databind.exc.InvalidFormatException;
//import com.pranhirefy.employee.dto.EmployeeBankDetailsDto;
//import com.pranhirefy.employee.exception.AccountTypeNotFoundException;
//import com.pranhirefy.employee.exception.BankIllegalPathException;
////import com.pranhirefy.employee.exception.InvalidAccountTypeSearchException;
//import com.pranhirefy.employee.exception.InvalidEmployeeBankDetailException;
//import com.pranhirefy.employee.exception.InvalidEmployeeBankDetailsException;
//import com.pranhirefy.employee.exception.NoBankFoundException;
//import com.pranhirefy.employee.service.EmployeeBankDetailsService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import com.fasterxml.jackson.core.JsonParseException;
//
//@RestController
//@RequestMapping("/api/bankdetails")
//@CrossOrigin(origins = "*")
////@Tag(name = "Employee Bank Details", description = "Operations related to employee bank details")
//public class EmployeeBankDetailsController {
//
//    private final EmployeeBankDetailsService bankDetailsService;
//
//    @Autowired
//    public EmployeeBankDetailsController(EmployeeBankDetailsService bankDetailsService) {
//        this.bankDetailsService = bankDetailsService;
//    }
//
//    
////    @PostMapping
////    @Operation(
////            operationId = "createEmployeeBankDetails",
////            summary = "Create Employee Bank Details",
////            description = "Creates a new bank detail record associated with a specific employee"
////        )
////    public ResponseEntity<EmployeeBankDetailsDto> createEmployeeBankDetails(@RequestBody EmployeeBankDetailsDto dto) {
////        EmployeeBankDetailsDto created = bankDetailsService.createEmployeeBank(dto);
////        return ResponseEntity.ok(created);
////    }
//    
//    @PostMapping("/create")
//    @Operation(
//        operationId = "createEmployeeBankDetails",
//        summary = "Create Employee Bank Details",
//        description = "Creates a new bank detail record associated with a specific employee"
//    )
//    public ResponseEntity<Map<String, Object>> createEmployeeBankDetails(@RequestBody EmployeeBankDetailsDto dto) {
//        EmployeeBankDetailsDto created = bankDetailsService.createEmployeeBank(dto);
//
//        Map<String, Object> response = new HashMap<>();
////        response.put("status", "success");
//        response.put("message", "Employee bank details successfully created with ID " + created.getEmpBankDetailId());
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
//    }
//
//
////    @Operation(summary = "Get all employee bank details")
//    @GetMapping("/getAll")
//    public ResponseEntity<List<EmployeeBankDetailsDto>> getAllEmployeeBankDetails() {
//        return ResponseEntity.ok(bankDetailsService.getAllEmployeeBank());
//    }
//
////    @Operation(summary = "Get employee bank details by ID")
//    @GetMapping("/get/{id}")
//    public ResponseEntity<EmployeeBankDetailsDto> getEmployeeBankDetailsById(@PathVariable Long id) {
//        return ResponseEntity.ok(bankDetailsService.getByEmployeeBankDetails(id));
//    }
//
////    @Operation(summary = "Update employee bank details")
////    @PutMapping("/update/{id}")
////    public ResponseEntity<EmployeeBankDetailsDto> updateEmployeeBankDetails(
////            @PathVariable Long id,
////            @RequestBody EmployeeBankDetailsDto dto) {
////        return ResponseEntity.ok(bankDetailsService.updateEmployeeBankDetails(id, dto));
////    }
//    
//    @PutMapping("/update/{id}")
//    public ResponseEntity<Map<String, Object>> updateEmployeeBankDetails(
//            @PathVariable Long id,
//            @RequestBody EmployeeBankDetailsDto dto) {
//
//        EmployeeBankDetailsDto updatedDto = bankDetailsService.updateEmployeeBankDetails(id, dto);
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("message", "Employee bank details updated successfully with ID " + updatedDto.getEmpBankDetailId());
////        response.put("data", updatedDto);
//
//        return ResponseEntity.ok(response);
//    }
//
//
//
////    @Operation(summary = "Delete employee bank details by ID")
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Map<String, Object>> deleteEmployeeBankDetails(@PathVariable Long id) {
//        bankDetailsService.deleteByEmployeeBankDetails(id);
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("status", "success");
//        response.put("message", "Employee bank detail with ID " + id + " has been soft deleted.");
//
//        return ResponseEntity.ok(response); // 200 OK with body
//    }
//    
//    
//   
//
//    
//    
//
//
//    
//    
//    // --- invalid path ---
//    @RequestMapping("/**")
//    public void handleInvalidPath() {
//       throw new BankIllegalPathException("Invalid API endpoint.");
//    }
//    
//    //--- exception handler ---
//
//@ExceptionHandler(BankIllegalPathException.class)
//public ResponseEntity<Map<String, Object>> handleBankIllegalPath(BankIllegalPathException ex) {
//    Map<String, Object> response = new HashMap<>();
////    response.put("status", "failed");
//    response.put("message", ex.getMessage());
//    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
//}
//    
//    
//    
//
//
////    @ExceptionHandler(HttpMessageNotReadableException.class)
////    public ResponseEntity<Map<String, Object>> handleInvalidFormat(HttpMessageNotReadableException ex) {
////        Map<String, Object> error = new HashMap<>();
////        error.put("status", "failed");
////
////        if (ex.getCause() instanceof InvalidFormatException) {
////            InvalidFormatException cause = (InvalidFormatException) ex.getCause();
////            String fieldName = cause.getPath().get(0).getFieldName();
////
////            error.put("message", "Invalid  Date format for field '" + fieldName + "'. Please provide a valid value.");
////        } else {
////            error.put("message", "Request body is missing or invalid. Please provide a valid JSON object.");
////        }
////
////        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
////    }
//
//
//@ExceptionHandler(HttpMessageNotReadableException.class)
//public static ResponseEntity<Map<String, Object>> handleInvalidFormat(HttpMessageNotReadableException ex) {
//    Map<String, Object> error = new HashMap<>();
//    error.put("status", "failed");
//
//    Throwable cause = ex.getCause();
//
//    if (cause instanceof InvalidFormatException) {
//        InvalidFormatException formatEx = (InvalidFormatException) cause;
//        String fieldName = formatEx.getPath().get(0).getFieldName();
//        error.put("message", "Invalid format for field '" + fieldName + "'. Please provide a valid value.");
//    } else if (cause instanceof JsonParseException) {
//        error.put("message", "Invalid JSON format. Please remove extra commas or fix syntax.");
//    } else {
//        error.put("message", "Request body is missing or invalid. Please provide a valid JSON object.");
//    }
//
//    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//}
//
//    
//    @ExceptionHandler(InvalidEmployeeBankDetailsException.class )
//    public ResponseEntity<Map<String, Object>> handleInvalidEmployeeBankDetails(InvalidEmployeeBankDetailsException ex) {
//        Map<String, Object> response = new HashMap<>();
////        response.put("status", "failed");
//        response.put("message", ex.getMessage());
//        response.put("errors", ex.getErrors());
//       
//        return ResponseEntity.badRequest().body(response); // 400 instead of 500
//    }
// 
//    @ExceptionHandler({InvalidEmployeeBankDetailException.class, NoBankFoundException.class})
//    public ResponseEntity<Map<String, Object>> handleBankDetailExceptions(RuntimeException ex) {
//        Map<String, Object> error = new HashMap<>();
////        error.put("status", "failed");
//        error.put("message", ex.getMessage());
//
//        if (ex instanceof InvalidEmployeeBankDetailException) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
//        } else if (ex instanceof NoBankFoundException) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
//        }
//
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
//    }
//    
//
//    
//
//}
//
package com.pranhirefy.employee.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.pranhirefy.employee.dto.EmployeeBankDetailsDto;
import com.pranhirefy.employee.exception.AccountTypeNotFoundException;
import com.pranhirefy.employee.exception.BankIllegalPathException;
import com.pranhirefy.employee.exception.InvalidEmployeeBankDetailException;
import com.pranhirefy.employee.exception.InvalidEmployeeBankDetailsException;
import com.pranhirefy.employee.exception.NoBankFoundException;
import com.pranhirefy.employee.service.EmployeeBankDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.core.JsonParseException;

@RestController
@RequestMapping("/api/bankdetails")
@CrossOrigin(origins = "*")
public class EmployeeBankDetailsController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeBankDetailsController.class);

    private final EmployeeBankDetailsService bankDetailsService;

    @Autowired
    public EmployeeBankDetailsController(EmployeeBankDetailsService bankDetailsService) {
        this.bankDetailsService = bankDetailsService;
    }

    @PostMapping("/create")
    @Operation(
        operationId = "createEmployeeBankDetails",
        summary = "Create Employee Bank Details",
        description = "Creates a new bank detail record associated with a specific employee"
    )
    public ResponseEntity<Map<String, Object>> createEmployeeBankDetails(@RequestBody EmployeeBankDetailsDto dto) {
        logger.info("Received request to create employee bank detail for employee ID: {}", dto.getEmployeeBasicDetailId());

        EmployeeBankDetailsDto created = bankDetailsService.createEmployeeBank(dto);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Employee bank details successfully created with ID " + created.getEmpBankDetailId());

        logger.info("Employee bank detail created successfully with ID: {}", created.getEmpBankDetailId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<EmployeeBankDetailsDto>> getAllEmployeeBankDetails() {
        logger.info("Fetching all employee bank details");
        return ResponseEntity.ok(bankDetailsService.getAllEmployeeBank());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<EmployeeBankDetailsDto> getEmployeeBankDetailsById(@PathVariable Long id) {
        logger.info("Fetching employee bank detail by ID: {}", id);
        return ResponseEntity.ok(bankDetailsService.getByEmployeeBankDetails(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateEmployeeBankDetails(
            @PathVariable Long id,
            @RequestBody EmployeeBankDetailsDto dto) {

        logger.info("Updating employee bank detail with ID: {}", id);

        EmployeeBankDetailsDto updatedDto = bankDetailsService.updateEmployeeBankDetails(id, dto);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Employee bank details updated successfully with ID " + updatedDto.getEmpBankDetailId());

        logger.info("Employee bank detail updated successfully with ID: {}", updatedDto.getEmpBankDetailId());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteEmployeeBankDetails(@PathVariable Long id) {
        logger.info("Deleting employee bank detail with ID: {}", id);

        bankDetailsService.deleteByEmployeeBankDetails(id);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Employee bank detail with ID " + id + " has been soft deleted.");

        logger.info("Employee bank detail soft deleted with ID: {}", id);
        return ResponseEntity.ok(response);
    }

    @RequestMapping("/**")
    public void handleInvalidPath() {
        logger.warn("Invalid API endpoint accessed");
        throw new BankIllegalPathException("Invalid API endpoint.");
    }

    @ExceptionHandler(BankIllegalPathException.class)
    public ResponseEntity<Map<String, Object>> handleBankIllegalPath(BankIllegalPathException ex) {
        logger.warn("Handling BankIllegalPathException: {}", ex.getMessage());

        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public static ResponseEntity<Map<String, Object>> handleInvalidFormat(HttpMessageNotReadableException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("status", "failed");

        Throwable cause = ex.getCause();

        if (cause instanceof InvalidFormatException) {
            InvalidFormatException formatEx = (InvalidFormatException) cause;
            String fieldName = formatEx.getPath().get(0).getFieldName();
            error.put("message", "Invalid format for field '" + fieldName + "'. Please provide a valid value.");
        } else if (cause instanceof JsonParseException) {
            error.put("message", "Invalid JSON format. Please remove extra commas or fix syntax.");
        } else {
            error.put("message", "Request body is missing or invalid. Please provide a valid JSON object.");
        }

        LoggerFactory.getLogger(EmployeeBankDetailsController.class).warn("HttpMessageNotReadableException: {}", error.get("message"));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidEmployeeBankDetailsException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidEmployeeBankDetails(InvalidEmployeeBankDetailsException ex) {
        logger.error("InvalidEmployeeBankDetailsException: {}", ex.getMessage());

        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        response.put("errors", ex.getErrors());

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler({InvalidEmployeeBankDetailException.class, NoBankFoundException.class})
    public ResponseEntity<Map<String, Object>> handleBankDetailExceptions(RuntimeException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("message", ex.getMessage());

        if (ex instanceof InvalidEmployeeBankDetailException) {
            logger.warn("InvalidEmployeeBankDetailException: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        } else if (ex instanceof NoBankFoundException) {
            logger.warn("NoBankFoundException: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        logger.error("Unexpected error: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}

