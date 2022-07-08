package com.gd.bookshopboot.util;

import com.gd.bookshopboot.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 添加自定义拦截器
     */
//   @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//
//       HandlerInterceptor interceptor = new LoginInterceptor();
//        //配置普通用户登录拦截器
//        registry.addInterceptor(interceptor).addPathPatterns("/user/admin/**"); //所有需要用户登录操作的所有请求
//        //配置管理员登录拦截器
//        registry.addInterceptor(interceptor).addPathPatterns("/*").excludePathPatterns("/admin/isLogin");
//    }

    /**
     * 解决前后端分离引发的跨域问题
     * 注意：如果想要在前端请求的时候携带cookie信息，allowedOriginPatterns的值不能设置为 *，
     * 需要将值设置为前端的工程路径，可以设置多个，每个用，隔开
     */
    /**
     * 跨域配置
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .maxAge(3600)
                .allowCredentials(true);
    }

}
