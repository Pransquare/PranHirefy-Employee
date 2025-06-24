package com.pranhirefy.employee.service.impl;

import com.pranhirefy.employee.dto.EmployeeProjectDetailsDto;
import com.pranhirefy.employee.entity.EmployeeProjectDetailsEntity;
import com.pranhirefy.employee.exception.EmployeeProjectDetailsNotFound;
import com.pranhirefy.employee.exception.NoProjectException;
import com.pranhirefy.employee.mapper.EmployeeProjectDetailsMapper;
import com.pranhirefy.employee.repository.EmployeeBasicDetailsRepository;
import com.pranhirefy.employee.repository.EmployeeProjectDetailsRepository;
import com.pranhirefy.employee.service.EmployeeProjectDetailsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeProjectDetailsServiceImpl implements EmployeeProjectDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeProjectDetailsServiceImpl.class);

    @Autowired
    private EmployeeProjectDetailsRepository projectRepository;

    @Autowired
    private EmployeeProjectDetailsMapper mapper;
    @Autowired
    private EmployeeBasicDetailsRepository employeeBasicDetailsRepository;


	@Override
	public EmployeeProjectDetailsDto create(EmployeeProjectDetailsDto dto) {
		
		EmployeeProjectDetailsEntity entity =mapper.toEntity(dto);
		EmployeeProjectDetailsEntity savedEntity =projectRepository.save(entity);
		return mapper.toDto(savedEntity);
		
		
		
		
		
	}

	@Override
	public EmployeeProjectDetailsDto getById(Long id) {
		EmployeeProjectDetailsEntity entity =projectRepository.findById(id)
		.orElseThrow(() -> new EmployeeProjectDetailsNotFound("Employee project  details " + id +"not found") );
		
		
		return mapper.toDto(entity);
	}

	@Override
	public List<EmployeeProjectDetailsDto> getAll() {
		List<EmployeeProjectDetailsEntity> entityList = 	projectRepository.findAll();
		return entityList.stream()
				.map(mapper::toDto)
				.collect(Collectors.toList());
		
		
	}

	@Override
	public EmployeeProjectDetailsDto update(Long id, EmployeeProjectDetailsDto dto) {
	    // Fetch existing entity by ID
	    EmployeeProjectDetailsEntity existingEntity = projectRepository.findById(id)
	        .orElseThrow(() -> new NoProjectException("Project with ID " + id + " not found"));

	    // Map DTO to entity
	    EmployeeProjectDetailsEntity updatedEntity = mapper.toEntity(dto);

	    // Set the existing ID to ensure update, not insert
	    updatedEntity.setEmpProjectId(id);

	    // Save updated entity
	    EmployeeProjectDetailsEntity savedEntity = projectRepository.save(updatedEntity);

	    // Return DTO
	    return mapper.toDto(savedEntity);
	}


	@Override
	public void delete(Long id) {
		projectRepository.deleteById(id);
		
		
		
	}

	@Override
	public List<EmployeeProjectDetailsDto> searchByProjectCode(String keyword) {
		List <EmployeeProjectDetailsEntity> entitys = projectRepository.searchByProjectCode(keyword);
		return entitys.stream()
				.map(mapper::toDto)
				.collect(Collectors.toList());
		
		
		
	}

    

//    @Override
//    public List<EmployeeProjectDetailsDto> searchProjectsByCode(String keyword) {
//        logger.info("Searching projects with keyword: {}", keyword);
//
//        if (keyword == null || keyword.trim().isEmpty()) {
//            throw new InvalidEmployeeProjectDetailsException("Search keyword must not be empty");
//        }
//
//        return projectRepository.searchByProjectCode(keyword.trim())
//                .stream()
//                .map(mapper::toDto)
//                .collect(Collectors.toList());
//    }
}
