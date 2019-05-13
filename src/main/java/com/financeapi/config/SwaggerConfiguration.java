package com.financeapi.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .useDefaultResponseMessages(false)
        .apiInfo(getApiInfo())
        .select()
        .paths(getApiPaths())
        .build();
  }

  private ApiInfo getApiInfo() {
    return new ApiInfoBuilder()
        .title("Finance Tracker API")
        .description("API Test Application")
        .contact(new Contact("", "", "nikolovblagoy@gmail.com"))
        .version("v1")
        .build();
  }

  private Predicate<String> getApiPaths() {
    return or(regex("/api/tracker/.*"));
  }
}
