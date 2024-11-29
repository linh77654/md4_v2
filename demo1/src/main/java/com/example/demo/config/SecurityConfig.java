package com.example.demo.config;

import com.example.security_blog.service.UserInforDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private UserInforDetailService userInforDetailService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userInforDetailService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        //Config các đường dẫn 0 cần đăng nhập

        http.authorizeHttpRequests((authorizeRequests) -> authorizeRequests.requestMatchers("/login", "/logout").permitAll());
        //Config các đường dẫn bắt buộc cần đăng nhập

        http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests.requestMatchers("/blogs/**", "/logout").authenticated());
        //cấu hình lại form login

        http.formLogin((formLogin) -> formLogin
                .usernameParameter("username")
                .passwordParameter("password")
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/blogs/list"));

        http.logout((logout) -> logout
                .deleteCookies("remove")
                .invalidateHttpSession(false)
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logout"));

        return http.build();
    }
}