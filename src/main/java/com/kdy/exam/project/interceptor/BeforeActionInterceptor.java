package com.kdy.exam.project.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kdy.exam.project.service.MemberService;
import com.kdy.exam.project.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class BeforeActionInterceptor implements HandlerInterceptor{
	@Autowired
	private MemberService memberService;
	
	//이런식으로 쓰면 인터셉터를 구현한거다라고 생각하기 (이해노)
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
		
		//이제는 Rq 객체가 자동으로 만들어지기 때문에 필요없음
		return HandlerInterceptor.super.preHandle(req, resp, handler);
	}
	
	
	

}
