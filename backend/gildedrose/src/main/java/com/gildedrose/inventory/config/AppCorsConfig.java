package com.gildedrose.inventory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Component
@Configuration
public class AppCorsConfig {
	@Bean
	CorsFilter corsFilter(){
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		
		config.setAllowCredentials(true);
		/*config.setAllowedOrigins(Arrays.asList(
									"http://kstmobile.com", 
									"http://localhost:8100",
									"http://localhost:4200",
									"http://localhost:8101",
									"*"
									)
								);*/
		config.addAllowedOriginPattern("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("DELETE");
		source.registerCorsConfiguration("/**", config);		
		
		return new CorsFilter(source);
	}		
}

