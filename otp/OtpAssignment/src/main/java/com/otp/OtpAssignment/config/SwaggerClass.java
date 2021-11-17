package com.otp.OtpAssignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerClass {
	@Bean
	public Docket swaggerApiConfig() {
		return new Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("com.otp")).build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
 
		return new ApiInfo("Swagger API Documentation", "This is an example of Swager Implementation", "", "",
				new Contact("Swager API Example", "http://localhost:9090/swagger-ui.html", ""), "", "");

	}
}
