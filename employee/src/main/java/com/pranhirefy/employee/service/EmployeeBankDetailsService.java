package com.pranhirefy.employee.service;

import java.util.List;

import com.pranhirefy.employee.dto.EmployeeBankDetailsDto;

public interface EmployeeBankDetailsService {
	
	EmployeeBankDetailsDto  createEmployeeBank (EmployeeBankDetailsDto dto);
	List<EmployeeBankDetailsDto> getAllEmployeeBank();
	
	EmployeeBankDetailsDto  getByEmployeeBankDetails (Long Id);
	EmployeeBankDetailsDto updateEmployeeBankDetails(Long Id,EmployeeBankDetailsDto dto);
	void deleteByEmployeeBankDetails(Long Id);
	
	

}
