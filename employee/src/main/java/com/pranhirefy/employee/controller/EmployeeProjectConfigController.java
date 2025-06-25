package com.pranhirefy.employee.controller;

import com.pranhirefy.employee.dto.EmployeeProjectConfigDTO;
import com.pranhirefy.employee.service.EmployeeProjectConfigService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/employee-project-config")
public class EmployeeProjectConfigController {

    private final EmployeeProjectConfigService service;

    public EmployeeProjectConfigController(EmployeeProjectConfigService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<EmployeeProjectConfigDTO> create(@RequestBody EmployeeProjectConfigDTO dto) {
        return ResponseEntity.ok(service.saveOrUpdate(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeProjectConfigDTO> update(@PathVariable Long id, @RequestBody EmployeeProjectConfigDTO dto) {
        dto.setEmployeeProjectConfigId(id);
        return ResponseEntity.ok(service.saveOrUpdate(dto));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeProjectConfigDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeProjectConfigDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}
