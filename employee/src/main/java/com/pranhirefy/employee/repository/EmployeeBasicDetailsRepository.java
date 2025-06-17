package com.pranhirefy.employee.repository;

import com.pranhirefy.employee.entity.EmployeeBasicDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EmployeeBasicDetailsRepository extends JpaRepository<EmployeeBasicDetailsEntity, Long> {
    Optional<EmployeeBasicDetailsEntity> findByEmployeeCode(String employeeCode);
}
