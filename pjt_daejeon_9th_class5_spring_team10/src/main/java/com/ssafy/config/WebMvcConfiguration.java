package com.ssafy.config;

import java.util.Arrays;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
@MapperScan(basePackages = {"com.ssafy.**.mapper"})
//WebMvcConfigurer를 상속받으면 웹에 관련된 설정을 할 수 있다. (servlet-context.xml)
public class WebMvcConfiguration implements WebMvcConfigurer{


	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
	
	}

	//properties 내부에 view resolver 등록
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/", ".jsp");
	}

	
}
