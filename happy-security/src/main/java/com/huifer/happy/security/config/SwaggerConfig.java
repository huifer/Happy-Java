package com.huifer.happy.security.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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

@Configuration
@EnableAutoConfiguration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket petApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.huifer.happy.security.controller"))
                .paths(PathSelectors.any())
                .build();

//    Api的一些描述信息
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("测试api")
                .description("对Api的描述")
                .contact(new Contact("huifer", "https://github.com/huifer", "huifer97@163.com"))
                .version("v1.0")
                .build();
        docket.apiInfo(apiInfo);

        return docket;
    }
}
