package org.angelholm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;


@Configuration
@EnableWebMvc
@ComponentScan("org.angelholm")
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/scripts/**").addResourceLocations("/WEB-INF/scripts/");
        registry.addResourceHandler("/styles/**").addResourceLocations("/WEB-INF/styles/");
        registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/images/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("/WEB-INF/fonts/");
        registry.addResourceHandler("/i18n/**").addResourceLocations("/WEB-INF/i18n/");

    }


    @Bean
    public UrlBasedViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        //resolver.setPrefix("/pages/");
        resolver.setSuffix(".zul");
        return resolver;
    }




}