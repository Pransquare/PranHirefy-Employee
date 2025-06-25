package com.pranhirefy.employee.repository;

import com.pranhirefy.employee.entity.EmployeeProjectConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeProjectConfigRepository extends JpaRepository<EmployeeProjectConfig, Long> {
    List<EmployeeProjectConfig> findByIsDeletedOrderByEmployeeProjectConfigIdDesc(String isDeleted);
    Optional<EmployeeProjectConfig> findByEmployeeProjectConfigIdAndIsDeleted(Long id, String isDeleted);
}
