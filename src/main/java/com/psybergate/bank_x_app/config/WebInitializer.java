package com.psybergate.bank_x_app.config;

import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Allows deploying this application in a WAR.
 */
@Configuration
@ComponentScan(basePackages = "com.psybergate.bank_x_app")
@SuppressWarnings("unused")
public class WebInitializer extends SpringBootServletInitializer {

}