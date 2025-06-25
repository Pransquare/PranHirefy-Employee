package com.pranhirefy.employee.controller;



import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class FallbackController implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<Map<String, String>> handleFallback(HttpServletRequest request) {
        return ResponseEntity.status(404)
                .body(Collections.singletonMap("message", "Invalid Base URL. Please check your endpoint."));
    }

    // Optional (for older Spring Boot versions)
    public String getErrorPath() {
        return "/error";

    }
}
