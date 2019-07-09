package com.cafe24.shoppingmall.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.cafe24.shoppingmall.controller", "com.cafe24.shoppingmall.exception" , "com.cafe24.shoppingmall.controller.api"})
//@Import({MVCConfig.class, SecurityConfig.class, MessageConfig.class, FileUploadConfig.class, SwaggerConfig.class})
public class WebConfig {
}
