package com.paymentapp.main.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {
	
	@Value("${app.api-key.header}") private String header;
    @Value("${app.api-key.value}") private String value;
    @Value("${app.api-key.enabled}") private boolean enabled;

    @Bean
    public FilterRegistrationBean<ApiKeyFilter> apiKeyFilter() {
        FilterRegistrationBean<ApiKeyFilter> reg = new FilterRegistrationBean<>();
        reg.setFilter(new ApiKeyFilter(header, value, enabled));
        reg.addUrlPatterns("/*");
        reg.setOrder(1);
        return reg;
    }

}
