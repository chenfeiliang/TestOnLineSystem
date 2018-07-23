package com.hubu.config;

import com.hubu.component.LoginInterceptor;
import com.hubu.component.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class CORSConfiguration extends WebMvcConfigurationSupport {


     @Override
      public void addCorsMappings(CorsRegistry registry) {
               registry.addMapping("/**")
                        .allowedHeaders("*")
                        .allowedMethods("*")
                        .allowedOrigins("*");
      }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/admin/**").excludePathPatterns("/admin/login.html");
        registry.addInterceptor(new UserInterceptor()).addPathPatterns("/front/**").excludePathPatterns("/front/login.html");
     }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }

}