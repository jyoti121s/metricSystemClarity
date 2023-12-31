package com.metricProject.metricSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@Configuration
public class MetricSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetricSystemApplication.class, args);
	}

		@Bean
		public Docket productApi() {
	      return new Docket(DocumentationType.SWAGGER_2)
	    		  .select()
	    		  .paths(PathSelectors.any())
	    		  .apis(RequestHandlerSelectors.basePackage("com.metricProject.metricSystem"))
	    		  .build();
	   }
}
