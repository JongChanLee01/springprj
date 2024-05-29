package com.shop2.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${uploadPath}")
    String uploadPath;


    // ResourceHandlerRegistry는 Spring MVC에서 자원(리소스)을
    // 처리하기 위한 핸들러를 등록하는 레지스트리.

    // 자원은 HTML, CSS, JavaScript, 이미지 등의 정적 파일을 의미
    // 이들 자원을 클라이언트에게 제공하기 위해서는 적절한 핸들러가 필요함
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations(uploadPath);
    }
}