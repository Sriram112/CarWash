package com.casestudy.washer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
public class WasherApplication {

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(WasherApplication.class, args);
	}

	@Bean
	public Docket SwaggerConfig(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/washers/**"))
				.apis(RequestHandlerSelectors.basePackage("com.casestudy.washer"))
				.build()
				.apiInfo(apiinform());
	}

	public ApiInfo apiinform() {

		//List<VendorExtension> vendorExtensions = new ArrayList<>();
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
//	private ApiInfo  apiinform(){
//		return new ApiInfo(
//				"Washers system",
//				"The Washer has all the below controls",
//				"1.0",
//				"Can be used by anyone testing the app",
//				new springfox.documentation.service.Contact("sriram","https://github.com/sriram112","sriram@gmail.com"),
//				"API license",
//				"https://github.com/sriram112",
//				Collections.emptyList());
//
//
//	}

}
