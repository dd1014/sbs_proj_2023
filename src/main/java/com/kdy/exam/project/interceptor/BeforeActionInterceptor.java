package com.kdy.exam.project.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class BeforeActionInterceptor implements HandlerInterceptor{
	
	//이런식으로 쓰면 인터셉터를 구현한거다라고 생각하기 (이해노)
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
		System.out.println("실행가능?");

		return HandlerInterceptor.super.preHandle(req, resp, handler);
	}
	
	
	

}