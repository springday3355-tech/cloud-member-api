package com.example.cloudmemberapi.common;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j // 로그를 남길 수 있게 해줌
@Component // 스프링이 관리하는 하나의 부품으로 등록함

public class LogInterceptor implements HandlerInterceptor { // 보안 요원의 행동 지침
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        String requestURI = request.getRequestURI(); // 사용자가 요청한 주소를 알아내서 URI에 저장
        String method = request.getMethod(); // 사용자가 어떤 방식으로 요청했는지 알아내서 method에 저장

        log.info ("[API - LOG] {} {}", method, requestURI); // 콘솔창에 예쁘게 출력하라는 명령어
        return true; // true 반환해야 다음 요청이(컨트롤러) 통과됌
    }
}
