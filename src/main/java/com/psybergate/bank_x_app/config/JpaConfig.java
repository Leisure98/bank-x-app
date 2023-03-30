package com.psybergate.bank_x_app.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * A configuration class for all the necessary JPA requirements.
 */
@Configuration
@ComponentScan(basePackages = "com.psybergate.bank_x_app")
@EnableJpaRepositories(basePackages = "com.psybergate.bank_x_app.repository")
@EntityScan(basePackages = "com.psybergate.bank_x_app.domain")
@EnableTransactionManagement
@SuppressWarnings("unused")
public class JpaConfig {

}