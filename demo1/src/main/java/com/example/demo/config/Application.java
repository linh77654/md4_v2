package com.example.demo.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class Application implements WebMvcConfigurer {

    // Khai báo bean Interceptor
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang"); // Thay đổi ngôn ngữ thông qua tham số 'lang'
        return localeChangeInterceptor;
    }

    // Thêm interceptor vào cấu hình web
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    // Cấu hình MessageSource để tìm các file messages.properties
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8"); // Đảm bảo mã hóa UTF-8 cho các file properties
        return messageSource;
    }

    // Cấu hình LocaleResolver để phân giải ngôn ngữ
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.ENGLISH); // Ngôn ngữ mặc định là tiếng Anh
        return localeResolver;
//        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
//        cookieLocaleResolver.setDefaultLocale(Locale.ENGLISH); // Đặt ngôn ngữ mặc định là tiếng Anh
//        cookieLocaleResolver.setCookieName("myAppLocaleCookie"); // Tên cookie
//        cookieLocaleResolver.setCookieMaxAge(3600); // Thời gian sống của cookie (1 giờ)
//        return cookieLocaleResolver;
    }
}