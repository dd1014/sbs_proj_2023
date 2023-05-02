package com.kdy.exam.project.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kdy.exam.project.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class NeedLoginInterceptor implements HandlerInterceptor {
	private Rq rq;

	public NeedLoginInterceptor(Rq rq) {
		this.rq = rq;
	}

	// 이 함수는 인터셉터를 적용하는 역할을 합니다.
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {

		// rq가 너 로그인햇냐 물어보기

		if (!rq.isLogined()) {
			String afterLoginUri = rq.getLoginUri();
			rq.printReplaceJs("로그인 후 이용해주세요.", afterLoginUri);
			return false;
		}

		return HandlerInterceptor.super.preHandle(req, resp, handler);
	}
}