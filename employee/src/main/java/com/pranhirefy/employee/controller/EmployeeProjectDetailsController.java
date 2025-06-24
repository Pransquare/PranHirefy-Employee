package com.pranhirefy.employee.controller;

import com.pranhirefy.employee.dto.EmployeeProjectDetailsDto;
import com.pranhirefy.employee.service.EmployeeProjectDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project-details")
public class EmployeeProjectDetailsController {

    @Autowired
    private EmployeeProjectDetailsService service;

    // ✅ Create project details
    @PostMapping
    public ResponseEntity<EmployeeProjectDetailsDto> createProject(@RequestBody EmployeeProjectDetailsDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    // ✅ Get all project details
    @GetMapping
    public ResponseEntity<List<EmployeeProjectDetailsDto>> getAllProjects() {
        return ResponseEntity.ok(service.getAll());
    }

    // ✅ Get project by ID
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeProjectDetailsDto> getProjectById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    // ✅ Update project details
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeProjectDetailsDto> updateProject(@PathVariable Long id,
                                                                   @RequestBody EmployeeProjectDetailsDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    // ✅ Delete project by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Search project by project code
    @GetMapping("/search")
    public ResponseEntity<List<EmployeeProjectDetailsDto>> searchByProjectCode(@RequestParam String keyword) {
        return ResponseEntity.ok(service.searchByProjectCode(keyword));
    }
}
