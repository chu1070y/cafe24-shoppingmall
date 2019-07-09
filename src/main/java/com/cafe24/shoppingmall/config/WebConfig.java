package com.cafe24.shoppingmall.config;

import org.apache.catalina.security.SecurityConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.cafe24.config.web.FileUploadConfig;
import com.cafe24.config.web.MVCConfig;
import com.cafe24.config.web.MessageConfig;
import com.cafe24.config.web.SwaggerConfig;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.cafe24.shoppingmall.controller", "com.cafe24.shoppingmall.exception" , "com.cafe24.shoppingmall.controller.api"})
@Import({MVCConfig.class, SecurityConfig.class, MessageConfig.class, FileUploadConfig.class, SwaggerConfig.class})
public class WebConfig {
}
