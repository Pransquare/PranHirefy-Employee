package com.pranhirefy.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pranhirefy.employee.entity.EmployeeBankDetailsEntity;
import com.pranhirefy.employee.entity.EmployeeBasicDetailsEntity;


public interface EmployeeBankDetailsRepository extends  JpaRepository<EmployeeBankDetailsEntity, Long> {

//	   List<EmployeeBankDetailsEntity> findByDeletedIgnoreCaseNot(String deleted);
	public List<EmployeeBankDetailsEntity> findAllByOrderByEmpBankDetailIdDesc();

	   boolean existsByBankAccountNoAndDeletedIgnoreCase(String bankAccountNo, String deleted);

	   
}
