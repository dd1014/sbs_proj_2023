package com.kdy.exam.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kdy.exam.project.interceptor.BeforeActionInterceptor;
import com.kdy.exam.project.interceptor.NeedLoginInterceptor;


@Configuration //
public class MyWebMvcConfigurer implements WebMvcConfigurer {
   // BeforeActionInterceptor불러오기
   @Autowired
   BeforeActionInterceptor beforeActionInterceptor;

   // BeforeActionInterceptor불러오기
   @Autowired
   NeedLoginInterceptor needLoginInterceptor;

   // InterceptorRegistry?
   // addPathPatterns 이 이후에 들어오는 모든 인터셉터 작동해, excluedPathPatterns 이거 제외하고.
   // 이 함수는 인터셉터를 적용하는 역할을 합니다.
   @Override // addInterceptors: 프로그램 실행되고 딱 한번만 실행.
   public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(beforeActionInterceptor).addPathPatterns("/**").excludePathPatterns("/error")
            .excludePathPatterns("/resource/**");
     
      registry.addInterceptor(needLoginInterceptor).addPathPatterns("/usr/article/write").addPathPatterns("/usr/article/doWrite")
      .addPathPatterns("/usr/article/modify").addPathPatterns("/usr/article/doModify").addPathPatterns("/usr/article/doDelete");
      
   }
}