package com.example.employeemanagement.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/** This class represents the configuration for CORS. */
@Configuration
public class CorsConfig {
  @Value("${CORS_ALLOWED_ORIGINS:http://localhost:3000}")
  private String corsAllowedOrigins;

  /**
   * Configure CORS. This is done by adding a CORS mapping that allows requests from all origins
   * using allowedOriginPatterns.
   *
   * @return WebMvcConfigurer
   */
  @Bean
  public WebMvcConfigurer corsConfigurer() {

    // Add CORS mappings
    return new WebMvcConfigurer() {

      /**
       * Add CORS mappings.
       *
       * @param registry CORS registry
       */
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        String[] allowedOrigins = corsAllowedOrigins.split(",");
        registry
            .addMapping("/**")
            .allowedOriginPatterns(allowedOrigins)
            .allowedMethods("GET", "POST", "PUT", "DELETE") // List all allowed HTTP methods
            .allowedHeaders("*") // Allows all headers
            .allowCredentials(true); // Allow credentials (cookies, etc.)
      }
    };
  }
}
