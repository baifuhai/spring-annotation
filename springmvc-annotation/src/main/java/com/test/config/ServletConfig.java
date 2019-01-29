package com.test.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//SpringMVC只扫描Controller；子容器
@ComponentScan(value = "com.test", includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class})}, useDefaultFilters = false)
@EnableWebMvc
public class ServletConfig extends WebMvcConfigurerAdapter /*implements WebMvcConfigurer*/ {

    //静态资源访问
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    //拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**").excludePathPatterns("/login/**");
        registry.addInterceptor(new MyInterceptor2()).addPathPatterns("/**").excludePathPatterns("/login/**");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    }

    //视图解析器
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        //registry.jsp();
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

}
