package com.pranhirefy.employee.repository;
 
import com.pranhirefy.employee.entity.EmployeeBankDetailsEntity;
import com.pranhirefy.employee.entity.EmployeeProjectDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
 
import java.util.List;
 
public interface EmployeeProjectDetailsRepository extends JpaRepository<EmployeeProjectDetailsEntity, Long> {
 
    // ✅ Get all non-deleted project records sorted by ID descending
    List<EmployeeProjectDetailsEntity> findByDeletedIgnoreCaseOrderByEmpProjectIdDesc(String deleted);
 
    // ✅ Check if a non-deleted record exists for the given ID
    boolean existsByEmpProjectIdAndDeletedIgnoreCase(Long id, String deleted);
 
    // ✅ Search only by projectCode for non-deleted records
    @Query("SELECT e FROM EmployeeProjectDetailsEntity e " +
           "WHERE e.deleted = 'No' AND LOWER(e.projectCode) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<EmployeeProjectDetailsEntity> searchByProjectCode(@Param("keyword") String keyword);
}