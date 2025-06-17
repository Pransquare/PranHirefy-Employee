package com.pranhirefy.employee.controller;

import com.pranhirefy.employee.dto.EmployeeBasicDetailsDto;
import com.pranhirefy.employee.exception.EmployeeNotFoundException;
import com.pranhirefy.employee.service.EmployeeBasicDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeBasicDetailsController {

    private final EmployeeBasicDetailsService employeeService;

    @Autowired
    public EmployeeBasicDetailsController(EmployeeBasicDetailsService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeBasicDetailsDto> createOrUpdate(@RequestBody EmployeeBasicDetailsDto dto) {
        EmployeeBasicDetailsDto saved = employeeService.createOrUpdateEmployee(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeBasicDetailsDto>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeBasicDetailsDto> getEmployeeById(@PathVariable Long id) {
        EmployeeBasicDetailsDto dto = employeeService.getEmployeeById(id);
        if (dto == null) {
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found");
        }
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.noContent().build();
    }
}
