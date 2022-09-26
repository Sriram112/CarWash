package com.casestudy.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
//import org.apache.logging.log4j.*;

import java.util.Collections;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
public class OrderApplication {

	//private static Logger orderlogger = LogManager.getLogger(OrderApplication.class.getName());

	public static void main(String[] args) {

		SpringApplication.run(OrderApplication.class, args);

//		orderlogger.info("click successful");
//		orderlogger.error("Database connection is failed");
//		orderlogger.debug("this is debug");

	}

	@Bean
	public Docket SwaggerConfig(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/orders/**"))
				.apis(RequestHandlerSelectors.basePackage("com.casestudy.order"))
				.build()
				.apiInfo(apiinfo());

	}
	public ApiInfo apiinfo() {

		Contact contact = new Contact(
				"sriram",
				"",
				"sai@g.com"
		);
		ApiInfo apiInfo = new ApiInfo(
				"user Service API",
				"sample API for car wash web development project", "1.0",
				"free to use", contact,
				"", "", Collections.emptyList());
		return apiInfo;
	}
}