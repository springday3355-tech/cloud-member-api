package com.example.cloudmemberapi.common;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer { // 보안요원 맨 앞에 배치


    private final LogInterceptor logInterceptor;

    @Override
    public void addInterceptors (InterceptorRegistry registry){
        registry.addInterceptor(logInterceptor)
                .order(1) // 1번째로 작업해라
                .addPathPatterns("/**") // 모든 요청 주소에 배치함
                .excludePathPatterns("/css/**", "/*.ico", "/error"); // 예외상황에선 배치하지 않음
    }
}
