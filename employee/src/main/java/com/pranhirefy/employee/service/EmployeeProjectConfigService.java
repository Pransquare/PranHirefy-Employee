package com.pranhirefy.employee.service;

import com.pranhirefy.employee.dto.EmployeeProjectConfigDTO;

import java.util.List;

public interface EmployeeProjectConfigService {
    EmployeeProjectConfigDTO saveOrUpdate(EmployeeProjectConfigDTO dto);
    List<EmployeeProjectConfigDTO> getAll();
    EmployeeProjectConfigDTO getById(Long id);
    void delete(Long id);
}
