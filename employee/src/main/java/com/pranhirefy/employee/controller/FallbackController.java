package com.pranhirefy.employee.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

//    @RequestMapping(value = "/**")
//    public ResponseEntity<String> fallback() {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                             .body("Invalid base URL. Please check the API endpoint.");
//    }
	@RequestMapping(value = "/**")
    public ResponseEntity<Map<String, Object>> fallback() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "failed");
        response.put("message", "Invalid base URL. Please check the API endpoint.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
