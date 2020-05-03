package com.learning.portal.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
                .apiInfo(apiInfo()).select().paths(postPaths()).build();
    }

    private Predicate<String> postPaths() {
        return or(regex("/course.*"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Course Request API")
                .description("We are trying to build an online learning portal that will offer pocket friendly, advance courses to students. On our website a user can browse through a catalogue of courses which are designed to upskill them particularly in the context of requirements in modern industries. Some of the courses will be free while some will be paid. ")
                .termsOfServiceUrl("http://xxx.com")
                .contact("dipakprasad53@gmail.com").license("JavaInUse License")
                .licenseUrl("dipakprasad53@gmail.com").version("1.0").build();
    }

}
