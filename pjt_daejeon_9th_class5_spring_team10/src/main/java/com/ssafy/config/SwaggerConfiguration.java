package com.ssafy.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration // 스프링 실행시 설정파일
@EnableSwagger2 // Swagger2를 사용
@SuppressWarnings("unchecked") // warning 제거
public class SwaggerConfiguration {

//	Swagger-UI 2.x 확인
//	http://localhost[:8080]/{your-app-root}/swagger-ui.html
//	Swagger-UI 3.x 확인
//	http://localhost[:8080]/{your-app-root}/swagger-ui/index.html

	private String version = "V1";
	private String title = "SSAFY Enjoy Trip API " + version;

	private ApiInfo apiInfo() {
		String descript = "Enjoy Trip API Reference for Developers<br>";
//		descript += "<img src=\"http://localhost:9999/vue/static/assets/img/ssafy_logo.png\">";
		return new ApiInfoBuilder().title(title).description(descript).version("1.0").build();
	}


    
	// API마다 구분짓기 위한 설정.
	@Bean
	public Docket userApi() {
		return getDocket("회원", Predicates.or(PathSelectors.regex("/user.*")));
	}

	@Bean
	public Docket searchApi() {
		return getDocket("게시판", Predicates.or(PathSelectors.regex("/notice.*")));
	}

	@Bean
	public Docket commonApi() {
		return getDocket("여행지", Predicates.or(PathSelectors.regex("/attraction.*")));

	}
	@Bean
	public Docket planBoardApi() {
		return getDocket("플랜게시판", Predicates.or(PathSelectors.regex("/planboard.*")));

	}
		
	@Bean
	public Docket allApi() {
		return getDocket("전체", Predicates.or(PathSelectors.regex("/*.*")));
	}

	public Docket getDocket(String groupName, Predicate<String> predicate) {
//		List<ResponseMessage> responseMessages = new ArrayList<ResponseMessage>();
//		responseMessages.add(new ResponseMessageBuilder().code(200).message("OK !!!").build());
//		responseMessages.add(new ResponseMessageBuilder().code(500).message("서버 문제 발생 !!!").responseModel(new ModelRef("Error")).build());
//		responseMessages.add(new ResponseMessageBuilder().code(404).message("페이지를 찾을 수 없습니다 !!!").build());
		List<Predicate<RequestHandler>> predicateList = new ArrayList<>();
		predicateList.add(RequestHandlerSelectors.basePackage("com.ssafy.attraction.controller"));
		predicateList.add(RequestHandlerSelectors.basePackage("com.ssafy.board.controller"));
		predicateList.add(RequestHandlerSelectors.basePackage("com.ssafy.member.controller"));
		predicateList.add(RequestHandlerSelectors.basePackage("com.ssafy.plan.controller"));		
		predicateList.add(RequestHandlerSelectors.basePackage("com.ssafy.rest.controller"));
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName(groupName).apiInfo(apiInfo())
				.select()
				.apis(Predicates.or(predicateList))
				.paths(predicate)
				.apis(RequestHandlerSelectors.any()).build();
//				.useDefaultResponseMessages(false)
//				.globalResponseMessage(RequestMethod.GET,responseMessages);
	}

	// swagger ui 설정.
	@Bean
	public UiConfiguration uiConfig() {
		return UiConfigurationBuilder.builder().displayRequestDuration(true).validatorUrl("").build();
	}

}
