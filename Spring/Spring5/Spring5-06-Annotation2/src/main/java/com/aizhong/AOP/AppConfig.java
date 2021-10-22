package com.aizhong.AOP;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.aizhong.AOP")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppConfig {
}
