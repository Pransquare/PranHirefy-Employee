//package com.pranhirefy.employee.service;
//
//import com.pranhirefy.employee.dto.EmployeeFamilyDetailsDTO;
//import java.util.List;
//
//public interface EmployeeFamilyDetailsService {
//    EmployeeFamilyDetailsDTO saveOrUpdate(EmployeeFamilyDetailsDTO dto);
//    List<EmployeeFamilyDetailsDTO> getAll();
//    EmployeeFamilyDetailsDTO getById(Long id);
//    void delete(Long id);
//}


//
//package com.pranhirefy.employee.service;
//
//import com.pranhirefy.employee.dto.EmployeeFamilyDetailsDTO;
//import java.util.List;
//
//public interface EmployeeFamilyDetailsService {
//
//    EmployeeFamilyDetailsDTO saveOrUpdate(EmployeeFamilyDetailsDTO dto);
//
//    List<EmployeeFamilyDetailsDTO> getAll();
//
//    EmployeeFamilyDetailsDTO getById(Long id);
//
//    void delete(Long id); // ✅ This now performs a soft delete
//}





package com.pranhirefy.employee.service;

import com.pranhirefy.employee.dto.EmployeeFamilyDetailsDTO;
import java.util.List;

public interface EmployeeFamilyDetailsService {

    EmployeeFamilyDetailsDTO saveOrUpdate(EmployeeFamilyDetailsDTO dto);

    List<EmployeeFamilyDetailsDTO> getAll();

    EmployeeFamilyDetailsDTO getById(Long id);

    void delete(Long id);

    // 🔍 Search method
    List<EmployeeFamilyDetailsDTO> search(String name, String relation);
}

