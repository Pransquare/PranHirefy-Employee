package com.pranhirefy.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
//@OpenAPIDefinition( info = @Info (
//        title = "Employee Bank Details API",
//        version = "1.0",
//        description = "API documentation for managing employee bank details"
//    ),servers = {
//            @Server(url = "http://localhost:8080", description = "Local Server")
//        } )
public class EmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}

}
