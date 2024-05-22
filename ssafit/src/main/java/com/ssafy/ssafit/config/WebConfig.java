package com.ssafy.ssafit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.ssafit.interceptor.SessionInterceptor;
@Configuration
public class WebConfig implements WebMvcConfigurer {

    
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
	
//	@Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // 인터셉터를 등록합니다.
//        registry.addInterceptor(new SessionInterceptor())
//                .addPathPatterns("/**") // 모든 경로에 인터셉터를 적용합니다.
//                .excludePathPatterns("/public/**"); // 특정 경로를 제외할 수도 있습니다.
//    }
}
