package com.kdy.exam.project.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class NeedLoginInterceptor implements HandlerInterceptor{

		
		// 이 함수는 인터셉터를 적용하는 역할을 합니다.
		@Override
		   public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
		      
		      System.out.println("로그인 필요!!");

		      return HandlerInterceptor.super.preHandle(req, resp, handler);
		   }
}