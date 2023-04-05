package com.wounom.kaoyanircpadmin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author litind
 * @version 1.0
 * @date 2023/3/31 13:37
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket creatRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com"))
                .paths(PathSelectors.any()).build();
    }

    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("考研信息发布及交流平台-管理端-API")
                .version("1.0")
                .description("KaoYanIRCP-admin")
                .build();
    }
}

