package com.casestudy.user;

import com.casestudy.user.repository.RatingRepo;
import org.springframework.beans.factory.annotation.Autowired;
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

@EnableEurekaClient
@EnableSwagger2
@SpringBootApplication
public class UserApplication{
	@Autowired
	private RatingRepo rr;

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

	@Bean
	public Docket SwaggerConfig(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/users/**"))
				.apis(RequestHandlerSelectors.basePackage("com.casestudy.user"))
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
