package com.ssafy.ssafit.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class SessionInterceptor implements HandlerInterceptor{
	
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173") // 클라이언트의 주소
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용하는 HTTP 메서드
                .allowedHeaders("*"); // 허용하는 헤더
    }

}
