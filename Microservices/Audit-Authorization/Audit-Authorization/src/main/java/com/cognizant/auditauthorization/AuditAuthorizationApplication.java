package com.cognizant.auditauthorization;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
public class AuditAuthorizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuditAuthorizationApplication.class, args);
	}

	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.cognizant.auditauthorization"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiDetails());
	}
	
	public ApiInfo apiDetails(){
		   return new ApiInfo(
		         "Authorization API",
		         "This microservice is used with anonymous access to Generate JWT",
		         "1.0",
		         "Free to use",
		         new springfox.documentation.service.Contact("AuditManagementPortal","ss","audit@gmail.com"),
		         "Api License",
		         "http://localhost:8084/v2/api-docs",
		         Collections.emptyList()
		   );
		}
}
