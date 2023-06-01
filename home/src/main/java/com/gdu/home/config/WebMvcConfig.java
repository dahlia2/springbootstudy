package com.gdu.home.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gdu.home.intercept.LoginCheckInterceptor;
import com.gdu.home.util.MyFileUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	// field
	private final LoginCheckInterceptor loginInterceptor;
	private final MyFileUtil myFileUtil;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	
		/* 포함되는 항목들 선택 */
		registry.addInterceptor(loginInterceptor)
		  .addPathPatterns("/bbs/write.html", "/upload/write.html")
		  .addPathPatterns("/user/logout.do");
	
		/* 제외할 항목들 선택 */
		registry.addInterceptor(loginInterceptor)
		  .addPathPatterns("/**")  				   // 모든 요청
		  .excludePathPatterns("/user/leave.do");  // 제외할 요청
	
	}
	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/imagesLoad/**")
		.addResourceLocations("file:" + myFileUtil.getSummernoteImagePath() + "/");
	}
	

}
