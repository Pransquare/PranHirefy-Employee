package com.pranhirefy.employee.service;

import com.pranhirefy.employee.dto.EmployeeBasicDetailsDto;
import java.util.List;

public interface EmployeeBasicDetailsService {
    EmployeeBasicDetailsDto createOrUpdateEmployee(EmployeeBasicDetailsDto dto);
    List<EmployeeBasicDetailsDto> getAllEmployees();
    EmployeeBasicDetailsDto getEmployeeById(Long id);
    void deleteEmployeeById(Long id);
}
