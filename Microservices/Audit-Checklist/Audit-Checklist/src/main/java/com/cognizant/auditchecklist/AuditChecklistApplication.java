package com.cognizant.auditchecklist;

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
public class AuditChecklistApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuditChecklistApplication.class, args);
	}
	
	/**
	 * 
	 * @return Docket object
	 * We are creating a Docket bean in a Spring Boot configuration to configure
	 * Swagger 2 for the application. 
	 * A Springfox Docket instance provides the primary API configuration with sensible defaults 
	 * and convenience methods for configuration
	 *
	 */

	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.cognizant.auditchecklist"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiDetails());
	}
	
	
	/**
	 * 
	 * @return ApiInfo object
	 * ApiInfo allows to provide Customized information for Docket Swagger_2
	 * Customized Information -
	 * 	 Title for API
	 * 	 Description of API
	 * 	 Terms of Service Url
	 * 	 License
	 * 	 Contact Information
	 * 	 Version of API
	 * 		
	 */
	
	public ApiInfo apiDetails(){
		   return new ApiInfo(
		         "Audit Checklist API",
		         "The intent of this Microservice is to provide the list of questions for Audit checklist. Post Authorization using JWT, the questions will be used to display the questions on the Web UI",
		         "1.0",
		         "Free to use",
		         new springfox.documentation.service.Contact("AuditManagementPortal","ss","audit@gmail.com"),
		         "Api License",
		         "http://localhost:8081/v2/api-docs",
		         Collections.emptyList()
		   );
		}

}
