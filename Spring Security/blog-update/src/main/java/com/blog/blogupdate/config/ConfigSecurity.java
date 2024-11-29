package com.blog.blogupdate.config;

import com.blog.blogupdate.service.iml.UserInforDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class ConfigSecurity {

    @Autowired
    private UserInforDetailService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.disable())
//                Config các đường dẫn không cần đăng nhập
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers("/","/blog", "/login", "/logoutSuccessful").permitAll())
//                Config các đường dẫn bắt buộc cần đăng nhập
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers("/blog/create", "/logout").authenticated());
//              Cấp quyền cho User
                http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                                .requestMatchers("/blog/create").hasRole("USER"));
//              Cấp quyền cho ADMin
                 http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        .requestMatchers("/blog/create","/login","/blog").hasRole("ADMIN"));
//              cấp quyền cho user và admin
                 http.authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/userInfo", "/blog/delete").hasAnyRole
                                         ("USER", "ADMIN"))
//               Cấu hình lại form login
                .formLogin((formLogin) ->
                        formLogin
                                .usernameParameter("username")
                                .passwordParameter("password")
                                .loginPage("/login")
                                .failureUrl("/login?error=true")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/blog"))
                .logout((logout) ->
                        logout.deleteCookies("remove")
                                .invalidateHttpSession(false)
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/logoutSuccessful"));
        return http.build();
    }
}