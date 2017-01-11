package com.greenmile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class,
        })
public class GreenmileApplication {
	
//	@Bean
//    public WebSecurityConfigurerAdapter webSecurityConfigurerAdapter() {
//        return new ApplicationSecurity();
//    }

	public static void main(String[] args) {
		SpringApplication.run(GreenmileApplication.class, args);
	}
}
