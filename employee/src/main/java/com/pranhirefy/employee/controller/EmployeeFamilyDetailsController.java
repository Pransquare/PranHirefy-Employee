//package com.pranhirefy.employee.controller;
//
//import com.fasterxml.jackson.databind.JsonMappingException.Reference;
//import com.fasterxml.jackson.databind.exc.InvalidFormatException;
//import com.pranhirefy.employee.dto.EmployeeFamilyDetailsDTO;
//import com.pranhirefy.employee.exception.AlreadyExistsException;
//import com.pranhirefy.employee.exception.CombinedValidationException;
//import com.pranhirefy.employee.exception.EmptyRequestBodyException;
//import com.pranhirefy.employee.exception.ResourceNotFoundException;
//import com.pranhirefy.employee.service.EmployeeFamilyDetailsService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Tag(name = "Employee Family Details", description = "APIs for managing employee family details")
//@CrossOrigin(origins = "*")
//@RestController
//@RequestMapping("/api/employee-family")
//public class EmployeeFamilyDetailsController {
//
//    private static final Logger logger = LoggerFactory.getLogger(EmployeeFamilyDetailsController.class);
//
//    @Autowired
//    private EmployeeFamilyDetailsService service;
//
//    @Operation(summary = "Create new employee family detail")
//    @PostMapping("/create")
//    public ResponseEntity<String> save(@RequestBody(required = false) EmployeeFamilyDetailsDTO dto) {
//        logger.info("POST /create called with body: {}", dto);
//        EmployeeFamilyDetailsDTO saved = service.saveOrUpdate(dto);
//        logger.info("Successfully created EmployeeFamilyDetail with ID: {}", saved.getEmpFamilyDetailId());
//        return ResponseEntity.ok("Created successfully" + saved.getEmpFamilyDetailId());
//    }
//
//    @Operation(summary = "Update existing employee family detail")    
//    @PutMapping("/update/{id}")
//    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody EmployeeFamilyDetailsDTO dto) {
//        logger.info("PUT /update called with ID: {} and body: {}", id, dto);
//        dto.setEmpFamilyDetailId(id);  // Ensure ID is set from the path
//        EmployeeFamilyDetailsDTO updated = service.saveOrUpdate(dto);
//        logger.info("Successfully updated EmployeeFamilyDetail with ID: {}", updated.getEmpFamilyDetailId());
//        return ResponseEntity.ok("Updated successfully " + updated.getEmpFamilyDetailId());
//    }
//
//
//    @Operation(summary = "Get all active employee family details")
//    @GetMapping("/all")
//    public ResponseEntity<List<EmployeeFamilyDetailsDTO>> getAll() {
//        logger.info("GET /all called");
//        List<EmployeeFamilyDetailsDTO> list = service.getAll();
//        logger.info("Retrieved {} employee family records", list.size());
//        return ResponseEntity.ok(list);
//    }
//
//    @Operation(summary = "Get employee family detail by ID")
//    @GetMapping("/{id}")
//    public ResponseEntity<EmployeeFamilyDetailsDTO> getById(@PathVariable Long id) {
//        logger.info("GET /{} called", id);
//        EmployeeFamilyDetailsDTO dto = service.getById(id);
//        logger.info("Retrieved EmployeeFamilyDetail: {}", dto);
//        return ResponseEntity.ok(dto);
//    }
//
//    @Operation(summary = "Soft delete employee family detail by ID")
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<String> delete(@PathVariable Long id) {
//        logger.info("DELETE /delete/{} called", id);
//        service.delete(id);
//        logger.info("Successfully soft deleted EmployeeFamilyDetail with ID: {}", id);
//        return ResponseEntity.ok("Deleted successfully");
//    }
//
//   
//    
//    
//    @Operation(summary = "Search employee family details by name or relation")
//    @GetMapping("/search")
//    public ResponseEntity<?> searchFamilyDetails(
//            @RequestParam(required = false) String name,
//            @RequestParam(required = false) String relation) {
//
//        logger.info("GET /search called with name='{}', relation='{}'", name, relation);
//
//        boolean isNameValid = name != null && !name.trim().isEmpty();
//        boolean isRelationValid = relation != null && !relation.trim().isEmpty();
//
//        if (!isNameValid && !isRelationValid) {
//            logger.warn("Search failed: missing parameters");
//            return ResponseEntity.badRequest().body(
//                    Map.of("message", "At least one valid search parameter name is required.")
//            );
//        }
//
//        List<EmployeeFamilyDetailsDTO> results = service.search(name, relation);
//
//        if (results.isEmpty()) {
//            logger.warn("No records found ");
//            throw new ResourceNotFoundException("No records found ");
//        }
//
//        logger.info("Found {} matching results", results.size());
//        return ResponseEntity.ok(results);
//    }
//
//    // 🛑 Exception Handlers
//
//    @ExceptionHandler(CombinedValidationException.class)
//    public ResponseEntity<Map<String, String>> handleValidation(CombinedValidationException ex) {
//        logger.error("Validation failed: {}", ex.getErrors());
//        return ResponseEntity.badRequest().body(ex.getErrors());
//    }
//
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<String> handleNotFound(ResourceNotFoundException ex) {
//        logger.warn("Resource not found: {}", ex.getMessage());
//        return ResponseEntity.status(404).body(ex.getMessage());
//    }
//
//    @ExceptionHandler(EmptyRequestBodyException.class)
//    public ResponseEntity<Map<String, String>> handleEmptyBody(EmptyRequestBodyException ex) {
//        logger.warn("Empty request body: {}", ex.getMessage());
//        Map<String, String> error = new HashMap<>();
//        error.put("body", ex.getMessage());
//        return ResponseEntity.badRequest().body(error);
//    }
//
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public ResponseEntity<Map<String, String>> handleTypeMismatch(HttpMessageNotReadableException ex) {
//        logger.error("Type mismatch or malformed JSON", ex);
//
//        Throwable cause = ex.getCause();
//        Map<String, String> error = new HashMap<>();
//
//        if (cause instanceof InvalidFormatException ife) {
//            for (Reference ref : ife.getPath()) {
//                String fieldName = ref.getFieldName();
//                if ("empBasicDetailId".equals(fieldName)) {
//                    error.put("empBasicDetailId", "Field 'empBasicDetailId' must be a number (integer).");
//                } else {
//                    error.put(fieldName, "Invalid format for field: " + fieldName);
//                }
//            }
//        } else {
//            error.put("body", "Request body is missing or malformed.");
//        }
//
//        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//    }
//    @ExceptionHandler(AlreadyExistsException.class)
//    public ResponseEntity<String> handleAlreadyExists(AlreadyExistsException ex) {
//        logger.warn("Already exists: {}", ex.getMessage());
//        return ResponseEntity.badRequest().body(ex.getMessage());
//    }
//
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleGeneric(Exception ex) {
//        logger.error("Internal server error", ex);
//        return ResponseEntity.status(500).body("Internal Server Error: " + ex.getMessage());
//    }
//}

package com.pranhirefy.employee.controller;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.pranhirefy.employee.dto.EmployeeFamilyDetailsDTO;
import com.pranhirefy.employee.exception.AlreadyExistsException;
import com.pranhirefy.employee.exception.CombinedValidationException;
import com.pranhirefy.employee.exception.EmptyRequestBodyException;
import com.pranhirefy.employee.exception.ResourceNotFoundException;
import com.pranhirefy.employee.service.EmployeeFamilyDetailsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Tag(name = "Employee Family Details", description = "APIs for managing employee family details")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/employee-family")
public class EmployeeFamilyDetailsController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeFamilyDetailsController.class);

    @Autowired
    private EmployeeFamilyDetailsService service;

    @Operation(summary = "Create new employee family detail")
    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> save(@RequestBody(required = false) EmployeeFamilyDetailsDTO dto) {
        logger.info("POST /create called with body: {}", dto);
        EmployeeFamilyDetailsDTO saved = service.saveOrUpdate(dto);
        logger.info("Successfully created EmployeeFamilyDetail with ID: {}", saved.getEmpFamilyDetailId());

        Map<String, String> response = new HashMap<>();
        response.put("message", "Created successfully");
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update existing employee family detail")    
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, String>> update(@PathVariable Long id, @RequestBody EmployeeFamilyDetailsDTO dto) {
        logger.info("PUT /update called with ID: {} and body: {}", id, dto);
        dto.setEmpFamilyDetailId(id);
        EmployeeFamilyDetailsDTO updated = service.saveOrUpdate(dto);
        logger.info("Successfully updated EmployeeFamilyDetail with ID: {}", updated.getEmpFamilyDetailId());

        Map<String, String> response = new HashMap<>();
        response.put("message", "Updated successfully");
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get all active employee family details")
    @GetMapping("/all")
    public ResponseEntity<List<EmployeeFamilyDetailsDTO>> getAll() {
        logger.info("GET /all called");
        List<EmployeeFamilyDetailsDTO> list = service.getAll();
        logger.info("Retrieved {} employee family records", list.size());
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Get employee family detail by ID")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeFamilyDetailsDTO> getById(@PathVariable Long id) {
        logger.info("GET /{} called", id);
        EmployeeFamilyDetailsDTO dto = service.getById(id);
        logger.info("Retrieved EmployeeFamilyDetail: {}", dto);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Soft delete employee family detail by ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {
        logger.info("DELETE /delete/{} called", id);
        service.delete(id);
        logger.info("Successfully soft deleted EmployeeFamilyDetail with ID: {}", id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Deleted successfully");
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Search employee family details by name or relation")
    @GetMapping("/search")
    public ResponseEntity<?> searchFamilyDetails(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String relation) {

        logger.info("GET /search called with name='{}', relation='{}'", name, relation);

        boolean isNameValid = name != null && !name.trim().isEmpty();
        boolean isRelationValid = relation != null && !relation.trim().isEmpty();

        if (!isNameValid && !isRelationValid) {
            logger.warn("Search failed: missing parameters");
            return ResponseEntity.badRequest().body(
                    Map.of("message", "At least one valid search parameter (name or relation) is required.")
            );
        }

        List<EmployeeFamilyDetailsDTO> results = service.search(name, relation);

        if (results.isEmpty()) {
            logger.warn("No records found");
            throw new ResourceNotFoundException("No records found");
        }

        logger.info("Found {} matching results", results.size());
        return ResponseEntity.ok(results);
    }

    

    @ExceptionHandler(CombinedValidationException.class)
    public ResponseEntity<Map<String, String>> handleValidation(CombinedValidationException ex) {
        logger.error("Validation failed: {}", ex.getErrors());
        return ResponseEntity.badRequest().body(ex.getErrors());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleNotFound(ResourceNotFoundException ex) {
        logger.warn("Resource not found: {}", ex.getMessage());
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(EmptyRequestBodyException.class)
    public ResponseEntity<Map<String, String>> handleEmptyBody(EmptyRequestBodyException ex) {
        logger.warn("Empty request body: {}", ex.getMessage());
        Map<String, String> error = new HashMap<>();
        error.put("body", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleTypeMismatch(HttpMessageNotReadableException ex) {
        logger.error("Type mismatch or malformed JSON", ex);

        Throwable cause = ex.getCause();
        Map<String, String> error = new HashMap<>();

        if (cause instanceof InvalidFormatException ife) {
            for (Reference ref : ife.getPath()) {
                String fieldName = ref.getFieldName();
                if ("empBasicDetailId".equals(fieldName)) {
                    error.put("empBasicDetailId", "Field 'empBasicDetailId' must be a number (integer).");
                } else {
                    error.put(fieldName, "Invalid format for field: " + fieldName);
                }
            }
        } else {
            error.put("body", "Request body is missing or malformed.");
        }

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<String> handleAlreadyExists(AlreadyExistsException ex) {
        logger.warn("Already exists: {}", ex.getMessage());
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneric(Exception ex) {
        logger.error("Internal server error", ex);
        return ResponseEntity.status(500).body("Internal Server Error: " + ex.getMessage());
    }
}

