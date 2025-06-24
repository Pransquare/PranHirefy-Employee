package com.pranhirefy.employee.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.pranhirefy.employee.dto.EmployeeBankDetailsDto;
import com.pranhirefy.employee.exception.BankIllegalPathException;
import com.pranhirefy.employee.exception.InvalidEmployeeBankDetailException;
import com.pranhirefy.employee.exception.InvalidEmployeeBankDetailsException;
import com.pranhirefy.employee.exception.NoBankFoundException;
import com.pranhirefy.employee.service.EmployeeBankDetailsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeBankDetailsController.class)
public class EmployeeBankDetailsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeBankDetailsService bankDetailsService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateEmployeeBankDetails() throws Exception {
        EmployeeBankDetailsDto dto = new EmployeeBankDetailsDto();
        dto.setEmpBankDetailId(1L);

        when(bankDetailsService.createEmployeeBank(any(EmployeeBankDetailsDto.class))).thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/bankdetails/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.message").value("Employee bank details successfully created with ID 1"));
    }

    @Test
    void testGetAllEmployeeBankDetails() throws Exception {
        when(bankDetailsService.getAllEmployeeBank()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/bankdetails/getAll"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetEmployeeBankDetailsById() throws Exception {
        EmployeeBankDetailsDto dto = new EmployeeBankDetailsDto();
        dto.setEmpBankDetailId(1L);

        when(bankDetailsService.getByEmployeeBankDetails(1L)).thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/bankdetails/get/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateEmployeeBankDetails() throws Exception {
        EmployeeBankDetailsDto dto = new EmployeeBankDetailsDto();
        dto.setEmpBankDetailId(1L);

        when(bankDetailsService.updateEmployeeBankDetails(Mockito.eq(1L), any(EmployeeBankDetailsDto.class))).thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/bankdetails/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteEmployeeBankDetails() throws Exception {
        doNothing().when(bankDetailsService).deleteByEmployeeBankDetails(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/bankdetails/delete/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.message").value("Employee bank detail with ID 1 has been soft deleted."));
    }

    @Test
    void testHandleInvalidPath() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/bankdetails/invalid/extra"))
                .andExpect(status().isNotFound());
    }
    @Test
    void testHandleInvalidFormat_WithInvalidFormatException() throws Exception {
        InvalidFormatException mockCause = mock(InvalidFormatException.class);
        JsonMappingException.Reference ref = new JsonMappingException.Reference("createdDate");
        when(mockCause.getPath()).thenReturn(List.of(ref));
        HttpMessageNotReadableException ex = new HttpMessageNotReadableException("Bad format", mockCause, null);

        ResponseEntity<Map<String, Object>> response =  EmployeeBankDetailsController.handleInvalidFormat(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().get("message").toString().contains("Invalid format for field"));
    }
    @Test
    void testHandleInvalidFormat_WithJsonParseException() {
        JsonParseException cause = mock(JsonParseException.class);
        HttpMessageNotReadableException ex = new HttpMessageNotReadableException("Bad JSON", cause, null);

        EmployeeBankDetailsController controller = new EmployeeBankDetailsController(bankDetailsService);
        ResponseEntity<Map<String, Object>> response =EmployeeBankDetailsController .handleInvalidFormat(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().get("message").toString().contains("Invalid JSON format"));
    }
    @Test
    void testHandleInvalidFormat_OtherCause() {
        // Create a cause that is NOT InvalidFormatException or JsonParseException
        Throwable otherCause = new RuntimeException("Generic error");
        HttpMessageNotReadableException ex = new HttpMessageNotReadableException("Some error", otherCause, null);

        EmployeeBankDetailsController controller = new EmployeeBankDetailsController(bankDetailsService);
        ResponseEntity<Map<String, Object>> response = controller.handleInvalidFormat(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().get("message").toString().contains("Request body is missing or invalid"));
    }
    @Test
    void testHandleBankDetailExceptions_InvalidEmployeeBankDetailException() {
        InvalidEmployeeBankDetailException ex = new InvalidEmployeeBankDetailException("Invalid ID");

        EmployeeBankDetailsController controller = new EmployeeBankDetailsController(bankDetailsService);
        ResponseEntity<Map<String, Object>> response = controller.handleBankDetailExceptions(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid ID", response.getBody().get("message"));
    }

    @Test
    void testHandleBankDetailExceptions_NoBankFoundException() {
        NoBankFoundException ex = new NoBankFoundException("Bank not found");

        EmployeeBankDetailsController controller = new EmployeeBankDetailsController(bankDetailsService);
        ResponseEntity<Map<String, Object>> response = controller.handleBankDetailExceptions(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Bank not found", response.getBody().get("message"));
    }
    @Test
    void testHandleBankDetailExceptions_InternalServerError() {
        // Pass a generic RuntimeException not listed in the if/else conditions
        RuntimeException ex = new RuntimeException("Unexpected error");

        EmployeeBankDetailsController controller = new EmployeeBankDetailsController(bankDetailsService);
        ResponseEntity<Map<String, Object>> response = controller.handleBankDetailExceptions(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Unexpected error", response.getBody().get("message"));
    }
    @Test
    void testHandleInvalidEmployeeBankDetails() {
        // Arrange
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("bankAccountNo", "Bank Account Number is required.");

        InvalidEmployeeBankDetailsException ex =
                new InvalidEmployeeBankDetailsException("Validation failed", errorMap);

        EmployeeBankDetailsController controller = new EmployeeBankDetailsController(bankDetailsService);

        // Act
        ResponseEntity<Map<String, Object>> response = controller.handleInvalidEmployeeBankDetails(ex);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Validation failed", response.getBody().get("message"));
        assertEquals(errorMap, response.getBody().get("errors"));
    }


   }
