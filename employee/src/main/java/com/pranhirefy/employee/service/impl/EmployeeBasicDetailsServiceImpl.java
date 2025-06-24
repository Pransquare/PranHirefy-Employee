package com.pranhirefy.employee.service.impl;

import com.pranhirefy.employee.dto.EmployeeBankDetailsDto;
import com.pranhirefy.employee.dto.EmployeeBasicDetailsDto;
import com.pranhirefy.employee.entity.EmployeeBasicDetailsEntity;
import com.pranhirefy.employee.exception.EmployeeBankDetailsNotFound;
import com.pranhirefy.employee.exception.EmployeeNotFoundException;
import com.pranhirefy.employee.mapper.EmployeeBasicDetailsMapper;
import com.pranhirefy.employee.repository.EmployeeBasicDetailsRepository;
import com.pranhirefy.employee.service.EmployeeBasicDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeBasicDetailsServiceImpl implements EmployeeBasicDetailsService {

    private final EmployeeBasicDetailsRepository employeeRepository;

    @Autowired
    public EmployeeBasicDetailsServiceImpl(EmployeeBasicDetailsRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeBasicDetailsDto createOrUpdateEmployee(EmployeeBasicDetailsDto dto) {
        EmployeeBasicDetailsEntity entity = EmployeeBasicDetailsMapper.toEntity(dto);
        EmployeeBasicDetailsEntity savedEntity = employeeRepository.save(entity);
        return EmployeeBasicDetailsMapper.toDto(savedEntity);
    }

    @Override
    public List<EmployeeBasicDetailsDto> getAllEmployees() {
        List<EmployeeBasicDetailsEntity> entityList = employeeRepository.findAll();
        return entityList.stream()
                .map(EmployeeBasicDetailsMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeBasicDetailsDto getEmployeeById(Long id) {
        EmployeeBasicDetailsEntity entity = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeBankDetailsNotFound("Employee bank details " + id +"not found"));
        return EmployeeBasicDetailsMapper.toDto(entity);
    }


    @Override
    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }
    
   
    	
    	
    	
    		
    	
    
}
