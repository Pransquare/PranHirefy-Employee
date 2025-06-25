package com.pranhirefy.employee.service.impl;

import com.pranhirefy.employee.dto.EmployeeProjectConfigDTO;
import com.pranhirefy.employee.entity.EmployeeProjectConfig;
import com.pranhirefy.employee.entity.EmployeeBasicDetailsEntity;
import com.pranhirefy.employee.exception.ResourceNotFoundException;
import com.pranhirefy.employee.mapper.EmployeeProjectConfigMapper;
import com.pranhirefy.employee.repository.EmployeeProjectConfigRepository;
import com.pranhirefy.employee.repository.EmployeeBasicDetailsRepository;
import com.pranhirefy.employee.service.EmployeeProjectConfigService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeProjectConfigServiceImpl implements EmployeeProjectConfigService {

    private final EmployeeProjectConfigRepository repository;
    private final EmployeeBasicDetailsRepository basicRepo;

    public EmployeeProjectConfigServiceImpl(EmployeeProjectConfigRepository repository,
                                            EmployeeBasicDetailsRepository basicRepo) {
        this.repository = repository;
        this.basicRepo = basicRepo;
    }

    @Override
    public EmployeeProjectConfigDTO saveOrUpdate(EmployeeProjectConfigDTO dto) {
        EmployeeBasicDetailsEntity employee = basicRepo.findById(dto.getEmployeeId())
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + dto.getEmployeeId()));
        EmployeeProjectConfig entity = EmployeeProjectConfigMapper.toEntity(dto, employee);

        if (entity.getEmployeeProjectConfigId() == null) {
            entity.setCreatedDate(LocalDateTime.now());
            entity.setModifiedDate(LocalDateTime.now());
            entity.setModifiedBy(dto.getCreatedBy());
            entity.setIsDeleted("No");
        } else {
            EmployeeProjectConfig existing = repository.findById(entity.getEmployeeProjectConfigId())
                .orElseThrow(() -> new ResourceNotFoundException("Project config not found"));
            entity.setCreatedDate(existing.getCreatedDate());
            entity.setCreatedBy(existing.getCreatedBy());
            entity.setIsDeleted(existing.getIsDeleted());
            entity.setModifiedDate(LocalDateTime.now());
        }

        EmployeeProjectConfig saved = repository.save(entity);
        return EmployeeProjectConfigMapper.toDTO(saved);
    }

    @Override
    public List<EmployeeProjectConfigDTO> getAll() {
        return repository.findByIsDeletedOrderByEmployeeProjectConfigIdDesc("No")
                .stream()
                .map(EmployeeProjectConfigMapper::toDTO)
                .toList();
    }

    @Override
    public EmployeeProjectConfigDTO getById(Long id) {
        return repository.findByEmployeeProjectConfigIdAndIsDeleted(id, "No")
                .map(EmployeeProjectConfigMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Project config not found"));
    }

    @Override
    public void delete(Long id) {
        EmployeeProjectConfig entity = repository.findByEmployeeProjectConfigIdAndIsDeleted(id, "No")
                .orElseThrow(() -> new ResourceNotFoundException("Project config not found"));
        entity.setIsDeleted("Yes");
        entity.setModifiedDate(LocalDateTime.now());
        repository.save(entity);
    }
}
