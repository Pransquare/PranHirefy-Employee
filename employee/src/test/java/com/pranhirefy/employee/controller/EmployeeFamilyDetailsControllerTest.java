package com.pranhirefy.employee.controller;

import com.pranhirefy.employee.dto.EmployeeFamilyDetailsDTO;
import com.pranhirefy.employee.exception.AlreadyExistsException;
import com.pranhirefy.employee.exception.CombinedValidationException;
import com.pranhirefy.employee.exception.EmptyRequestBodyException;
import com.pranhirefy.employee.exception.ResourceNotFoundException;
import com.pranhirefy.employee.service.EmployeeFamilyDetailsService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;
import com.fasterxml.jackson.databind.*;
import java.time.*;
import java.util.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@WebMvcTest(EmployeeFamilyDetailsController.class)
public class EmployeeFamilyDetailsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeFamilyDetailsService service;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testSearch_FoundResults() throws Exception {
        EmployeeFamilyDetailsDTO dto = new EmployeeFamilyDetailsDTO();
        dto.setEmpFamilyDetailId(1L);
        dto.setName("John");

        when(service.search(eq("John"), any())).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/employee-family/search?name=John"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John"));
    }


    @Test
    void testSearch_NotFound() throws Exception {
        when(service.search("John", "")).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/employee-family/search?name=John"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("No records found"));
    }


    @Test
    void testHandleValidationDirectly() {
        // Simulate errors
        Map<String, String> errors = new HashMap<>();
        errors.put("name", "Name is required");

        CombinedValidationException ex = new CombinedValidationException(errors);
        EmployeeFamilyDetailsController controller = new EmployeeFamilyDetailsController();

        ResponseEntity<Map<String, String>> response = controller.handleValidation(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Name is required", response.getBody().get("name"));
    }
    @Test
    void testHandleGenericException() throws Exception {
        // Simulate the service method throwing a generic exception
        when(service.saveOrUpdate(any())).thenThrow(new RuntimeException("Something went wrong"));
        
        mockMvc.perform(post("/api/employee-family/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"John\", \"relation\": \"Father\" }"))
            .andExpect(status().isInternalServerError())
            .andExpect(content().string("Internal Server Error: Something went wrong"));
    }

    @Test
    void testHandleAlreadyExistsException() throws Exception {
        // Simulate the service method throwing the AlreadyExistsException
        when(service.saveOrUpdate(any())).thenThrow(new AlreadyExistsException("Record already exists"));
        
        mockMvc.perform(post("/api/employee-family/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"John\", \"relation\": \"Father\" }"))
            .andExpect(status().isBadRequest())
            .andExpect(content().string("Record already exists"));
    }
    @Test
    void testHandleTypeMismatch_OtherError() throws Exception {
        // Sending malformed JSON will trigger HttpMessageNotReadableException
        mockMvc.perform(post("/api/employee-family/create") 
                .contentType(MediaType.APPLICATION_JSON) 
                .content("{invalid_json}") // INVALID JSON
            )
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.body").value("Request body is missing or malformed."));
    }
    @Test
    void testHandleTypeMismatch_OtherFieldError() throws Exception {
        // Sending a NON integer for the existing `empBasicDetailId` field
        String invalidJson = """
        {
          "empBasicDetailId": "invalid_int",
          "name": "John",
          "relation": "Brother",
          "isInsuranceRequired": "Yes",
          "dob": "2000-01-01T00:00:00"
        }
        """;

        mockMvc.perform(post("/api/employee-family/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidJson))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.empBasicDetailId").value("Field 'empBasicDetailId' must be a number (integer)."));
    }



    @Test
    void testHandleTypeMismatch_InvalidFormatException() throws Exception {
        // Sending invalid 'empBasicDetailId' (string instead of number) to trigger InvalidFormatException
        String invalidJson = "{\"empBasicDetailId\": \"invalid_number\", \"name\": \"John\"}";

        mockMvc.perform(post("/api/employee-family/create") // using create endpoint
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidJson))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.empBasicDetailId").value("Field 'empBasicDetailId' must be a number (integer)."));
    }


    @Test
    void testHandleTypeMismatch_GenericMalformedBody() throws Exception {
        // Sending completely malformed JSON
        String malformedJson = "{name: \"John\""; // Missing quotes or bracket error

        mockMvc.perform(post("/api/employee-family/create") // using create endpoint
                .contentType(MediaType.APPLICATION_JSON)
                .content(malformedJson))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.body").value("Request body is missing or malformed."));
    }


    @Test
    void testHandleEmptyBodyDirectly() {
        EmptyRequestBodyException ex = new EmptyRequestBodyException("Request body is missing");
        EmployeeFamilyDetailsController controller = new EmployeeFamilyDetailsController();

        ResponseEntity<Map<String, String>> response = controller.handleEmptyBody(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Request body is missing", response.getBody().get("body"));
    }



    @Test
    void testSearch_MissingParams() throws Exception {
        mockMvc.perform(get("/api/employee-family/search"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message")
                          .value("At least one valid search parameter (name or relation) is required."));
    }

    @Test
    void testGetAll() throws Exception {
        EmployeeFamilyDetailsDTO dto = new EmployeeFamilyDetailsDTO();
        dto.setName("John");
        when(service.getAll()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/employee-family/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John"));
    }

    @Test
    void testGetById_Found() throws Exception {
        EmployeeFamilyDetailsDTO dto = new EmployeeFamilyDetailsDTO();
        dto.setName("John");
        when(service.getById(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/employee-family/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John"));
    }

    @Test
    void testGetById_NotFound() throws Exception {
        when(service.getById(1L)).thenThrow(new ResourceNotFoundException("Not found"));
        mockMvc.perform(get("/api/employee-family/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Not found"));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/api/employee-family/delete/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Deleted successfully"));
    }


    @Test
    void testSave() throws Exception {
        EmployeeFamilyDetailsDTO request = new EmployeeFamilyDetailsDTO();
        request.setName("John");

        EmployeeFamilyDetailsDTO response = new EmployeeFamilyDetailsDTO();
        response.setEmpFamilyDetailId(1L);

        when(service.saveOrUpdate(any())).thenReturn(response);

        mockMvc.perform(post("/api/employee-family/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Created successfully"));
    }

    

    
    @Test
    void testUpdate() throws Exception {
        EmployeeFamilyDetailsDTO request = new EmployeeFamilyDetailsDTO();
        request.setName("John");
        EmployeeFamilyDetailsDTO response = new EmployeeFamilyDetailsDTO();
        response.setEmpFamilyDetailId(1L);

        when(service.saveOrUpdate(any())).thenReturn(response);

        mockMvc.perform(put("/api/employee-family/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Updated successfully"));
    }

}
