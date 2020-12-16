package com.rooms.occupancy.manager.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.time.LocalDate;

@Configuration
@EnableSwagger2
@ConfigurationProperties("app.api")
public class SwaggerConfig {
	private static final Logger LOG = LoggerFactory.getLogger(SwaggerConfig.class);

	private String version;
	
	private String title;
	
	private String description;
	
	private String basePackage;
	
	private String contactName;
	
	private String contactEmail;
	
	@PostConstruct
	public void init() {
		LOG.info(basePackage);
	}
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.basePackage(basePackage))
			.paths(PathSelectors.any())
			.build()
			.directModelSubstitute(LocalDate.class, String.class)
			.directModelSubstitute(Timestamp.class, String.class)
			.apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
			.title(title)
			.description(description)
			.version(version)
			.contact(new Contact(contactName, null, contactEmail))
			.build();
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(final String version) {
		this.version = version;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(final String name) {
		this.title = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getBasePackage() {
		return basePackage;
	}

	public void setBasePackage(final String basePackage) {
		this.basePackage = basePackage;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(final String contactName) {
		this.contactName = contactName;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(final String contactEmail) {
		this.contactEmail = contactEmail;
	}
}