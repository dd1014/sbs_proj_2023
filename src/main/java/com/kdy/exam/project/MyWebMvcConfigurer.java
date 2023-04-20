package com.kdy.exam.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kdy.exam.project.interceptor.BeforeActionInterceptor;


@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer{
	
	//BerforeActionInterceptor 불러오기
	@Autowired
	BeforeActionInterceptor berforeActionInterceptor;
	
	// 이 함수는 인터셉터를 적용하는 역할을 합니다.
		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(berforeActionInterceptor).addPathPatterns("/**").excludePathPatterns("/error");
		}
	
	
}
