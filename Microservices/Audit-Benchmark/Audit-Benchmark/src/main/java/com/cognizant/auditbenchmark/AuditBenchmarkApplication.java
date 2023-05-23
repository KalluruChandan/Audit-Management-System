package com.cognizant.auditbenchmark;

/*
 * @Description
 *	 Audit benchmark Module is a Middleware Microservice that performs the following operations:
 *   Provides the acceptable number of answers with NO as the answer for various audit types
 *  
 */

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
public class AuditBenchmarkApplication {
		
	//Swagger Configuration
	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.cognizant.auditbenchmark"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiDetails());
	}
	
	public ApiInfo apiDetails(){
		   return new ApiInfo(
		         "Audit BenchMark API",
		         "This provides the acceptable number of answers with NO as the answer for various audit types",
		         "1.0",
		         "Free to use",
		         new springfox.documentation.service.Contact("AuditManagementPortal","ss","audit@gmail.com"),
		         "Api License",
		         "http://localhost:8082/v2/api-docs",
		         Collections.emptyList()
		   );
		}

	//main method
	public static void main(String[] args) {
		SpringApplication.run(AuditBenchmarkApplication.class, args);
	}

}
