package com.example.demo.config;

import com.example.demo.repositories.KhachHangRepository;
import com.example.demo.repositories.NhanVienRepository;
import com.example.demo.security.MySimpleUrlAuthenticationSuccessHandler;
import com.example.demo.security.UserInfoService;

import com.example.demo.security.oauth2.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor

public class SecurityConfig {
    private final NhanVienRepository repository;
    private final KhachHangRepository khachHangRepository;
    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;


    @Bean
    public UserDetailsService userDetailsService() {
        return new UserInfoService(repository, khachHangRepository);
    }


    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new MySimpleUrlAuthenticationSuccessHandler();
    }

    @Bean
    SecurityFilterChain securityFilterChainGmail(HttpSecurity http) throws Exception {
        http

                .authorizeHttpRequests()
                .requestMatchers("/","/dang-ky-tai-khoan","/login/dang-ky-tai-khoan","/login/quen-mat-khau","/quen-mat-khau", "/WEB-INF/**", "/lgin", "/logout", "/ban-hang-online/**", "/css/**", "/cssbanhang/**"
                        , "/documentation/**", "/icons/**", "/images/**", "/inHoaDon/**", "/js/**", "/jsbanhang/**", "/maqr/**", "/scss/**","/login"
                        , "uploads/**", "/vendor/**","/img/**").permitAll()
                .requestMatchers("/favicon.ico").permitAll()

                //ca admin ca nhan vien
                .requestMatchers("/chi-tiet-san-pham/**", "/ban-hang/**","/hoa-don/**",
                        "/khach-hang/**","/san-pham/**","/phieu-giam-gia/**"

                ).hasAnyAuthority("STAFF", "ADMIN")

                // chi admin
                .requestMatchers("/chi-tiet-san-pham/**", "/ban-hang/**",
                        "/hoa-don/**","/khach-hang/**","/nhan-vien/**","/chuc-vu/**",
                        "/san-pham/**","/chat-lieu/**","/co-ao/**","/mau-sac/**","/kich-co/**","/thuong-hieu",
                        "/phieu-giam-gia/**","/thong-ke/**",
                        "/home1").hasAuthority("ADMIN")

                //tat ca tai khoan
                .requestMatchers("/chi-tiet-san-pham/**", "/ban-hang/**",
                        "/hoa-don/**","/khach-hang/**","/nhan-vien/**","/ban-hang-online/**",
                        "/san-pham/**",
                        "/phieu-giam-gia/**").authenticated()


                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/security/login")

                .successHandler(myAuthenticationSuccessHandler())
                .and()
                .logout()
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403")
                .and()
                .csrf()
                .disable()
                .httpBasic()
                .and()
                .oauth2Login()
                .loginPage("/lgin")
                .defaultSuccessUrl("/ban-hang-online/gmail", true)
                .userInfoEndpoint().userService(customOAuth2UserService);
        return http.build();
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
}
