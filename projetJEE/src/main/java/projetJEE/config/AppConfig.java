/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJEE.config;
 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan; 
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer; 
import org.springframework.web.servlet.view.JstlView; 
import org.springframework.web.servlet.view.UrlBasedViewResolver; 
 
@Configuration 
@ComponentScan("projetJEE.controllers")
@EnableWebMvc 
public class AppConfig implements WebMvcConfigurer { 
 
    @Bean     
    public UrlBasedViewResolver setupViewResolver() { 
        UrlBasedViewResolver resolver = new UrlBasedViewResolver(); 
        resolver.setPrefix("/WEB-INF/JSP/");        
        resolver.setSuffix(".jsp");    
        resolver.setViewClass(JstlView.class);   
        return resolver;    
    } 
 
    @Override  
    public void addResourceHandlers(ResourceHandlerRegistry registry) {     
        registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");    
    }
}
