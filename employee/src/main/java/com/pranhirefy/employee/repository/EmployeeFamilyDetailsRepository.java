//
//package com.pranhirefy.employee.repository;
//
//import com.pranhirefy.employee.entity.EmployeeFamilyDetails;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface EmployeeFamilyDetailsRepository extends JpaRepository<EmployeeFamilyDetails, Long> {
//
//    List<EmployeeFamilyDetails> findByIsDeleted(String isDeleted);
//
//    Optional<EmployeeFamilyDetails> findByEmpFamilyDetailIdAndIsDeleted(Long id, String isDeleted);
//
//    // 🔍 Flexible search by name and relation
//    @Query("SELECT e FROM EmployeeFamilyDetails e " +
//           "WHERE e.isDeleted = 'No' " +
//           "AND (:name IS NULL OR LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%'))) " +
//           "AND (:relation IS NULL OR LOWER(e.relation) LIKE LOWER(CONCAT('%', :relation, '%')))")
//    List<EmployeeFamilyDetails> searchByNameAndRelation(@Param("name") String name,
//                                                        @Param("relation") String relation);
//}




package com.pranhirefy.employee.repository;

import com.pranhirefy.employee.entity.EmployeeFamilyDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeFamilyDetailsRepository extends JpaRepository<EmployeeFamilyDetails, Long> {

    // Updated: for descending order by empFamilyDetailId
    List<EmployeeFamilyDetails> findByIsDeletedOrderByEmpFamilyDetailIdDesc(String isDeleted);

    Optional<EmployeeFamilyDetails> findByEmpFamilyDetailIdAndIsDeleted(Long id, String isDeleted);

    @Query("SELECT e FROM EmployeeFamilyDetails e " +
           "WHERE e.isDeleted = 'No' " +
           "AND (:name IS NULL OR LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%'))) " +
           "AND (:relation IS NULL OR LOWER(e.relation) LIKE LOWER(CONCAT('%', :relation, '%')))")
    List<EmployeeFamilyDetails> searchByNameAndRelation(@Param("name") String name,
                                                        @Param("relation") String relation);
    boolean existsByEmpFamilyDetailIdAndIsDeleted(Long empFamilyDetailId, String isDeleted);
}
