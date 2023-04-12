package com.wounom.kaoyanircpadmin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/2 16:02
 */
@Configuration
public class fileConfig implements WebMvcConfigurer {
    private String imgPath="D:/JAVA/Project/KaoYanIRCPAdmin/images/";
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/images/firstpage/**").addResourceLocations("file:"+imgPath+"firstpage/");
        registry.addResourceHandler("/images/university/**").addResourceLocations("file:"+imgPath+"university/");
    }

}
