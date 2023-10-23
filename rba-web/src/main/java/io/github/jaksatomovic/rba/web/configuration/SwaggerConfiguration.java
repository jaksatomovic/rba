package io.github.jaksatomovic.rba.web.configuration;

import io.github.jaksatomovic.rba.web.controller.ClientController;
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

/**
 * This configuration bean enables the embedded Swagger document generation and publishing.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration
{

    private static final String API_INFO_VERSION = "v1";
    private static final String API_INFO_TITLE   = "RBA";
    private static final String GROUP_NAME       = "RBA GROUP";
    private static final String NAME             = "Jakša Tomović";
    private static final String GITHUB_IO        = "jaksatomovic.github.io";
    private static final String EMAIL            = "jaksa.tomovic@gmail.com";

    /**
     * Responsible for creating/exposing Swagger API.
     */
    @Bean
    public Docket swaggerMonitoringServiceApi()
    {
        return new Docket(DocumentationType.SWAGGER_2).groupName(GROUP_NAME)
                                                      .select()
                                                      .apis(RequestHandlerSelectors.basePackage(ClientController.class.getPackage().getName()))
                                                      .paths(PathSelectors.any())
                                                      .build()
                                                      .apiInfo(getApiInfo());
    }

    /**
     * Returns API info.
     *
     * @return apiInfo [{@link ApiInfo}] :: the api info
     */
    private ApiInfo getApiInfo()
    {
        Contact contact = new Contact(NAME, GITHUB_IO, EMAIL);
        return new ApiInfoBuilder().title(API_INFO_TITLE).version(API_INFO_VERSION).contact(contact).build();
    }
}