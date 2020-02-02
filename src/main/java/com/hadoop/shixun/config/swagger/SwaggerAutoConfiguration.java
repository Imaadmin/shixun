package com.hadoop.shixun.config.swagger;

import com.google.common.base.Predicates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
@ConditionalOnWebApplication
@ConditionalOnClass(Docket.class)
@Profile("!product")
@ConditionalOnProperty(prefix = "lb.swagger", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(SwaggerConfigProperties.class)
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
@Slf4j
public class SwaggerAutoConfiguration {

    private SwaggerConfigProperties configProperties;
    @Autowired
    public SwaggerAutoConfiguration(SwaggerConfigProperties properties) {
        this.configProperties = properties;
    }

    @PostConstruct
    public void validate() {
        log.info("===>> Swagger auto config : {}", this.configProperties);
    }

    @Bean
    public Docket swaggerSpringfoxDocket() {
        log.debug("Starting Swagger");
        StopWatch watch = new StopWatch();
        watch.start();
        Contact contact = new Contact(
                configProperties.getContactName(),
                configProperties.getContactUrl(),
                configProperties.getContactEmail());

        ApiInfo apiInfo =
                new ApiInfoBuilder().title(configProperties.getTitle())
                        .contact(contact)
                        .description(configProperties.getDescription())
                        .termsOfServiceUrl(configProperties.getTermsOfServiceUrl())
                        .version("v1.0")
                        .build();


        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .forCodeGeneration(true)
                .genericModelSubstitutes(ResponseEntity.class)
                .ignoredParameterTypes(java.sql.Date.class)
//                .directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
//                .directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
//                .directModelSubstitute(java.time.LocalDateTime.class, Date.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage(configProperties.getBasePackage()))
                .paths(Predicates.not(PathSelectors.regex("/error.*")))//错误路径不监控
                .paths(regex(configProperties.getPathRegex()))
                .build();
        watch.stop();
        log.info("Started Swagger in {} ms", watch.getTotalTimeMillis());
        return docket;
    }
}