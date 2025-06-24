package com.pranhirefy.employee.service;
 
import com.pranhirefy.employee.dto.EmployeeProjectDetailsDto;
 
import java.util.List;
 
public interface EmployeeProjectDetailsService {

	EmployeeProjectDetailsDto create(EmployeeProjectDetailsDto dto);
	EmployeeProjectDetailsDto getById(Long id);
	List<EmployeeProjectDetailsDto>getAll();
	EmployeeProjectDetailsDto update(Long id,EmployeeProjectDetailsDto dto);
	void delete(Long id);
	List<EmployeeProjectDetailsDto> searchByProjectCode(String keyword);


}