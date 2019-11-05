package com.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.study.common.security.AuthFailureHandler;
import com.study.common.security.AuthProvider;
import com.study.common.security.AuthSuccessHandler;

/**
 * Security Configuration
 */
@Configuration
@EnableWebSecurity
@EnableGlobalAuthentication
@ComponentScan(basePackages = {"com.study.*"})
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * 로그인 처리
	 */
    @Autowired
    AuthProvider authProvider;

    /**
     * 로그인 실패 후 처리
     */
    @Autowired
    AuthFailureHandler authFailureHandler;

    /**
     * 로그인 성공 후 처리
     */
    @Autowired
    AuthSuccessHandler authSuccessHandler;

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 로그인 없이 접근 가능한 URL
        web.ignoring().antMatchers( "/css/**"
        		                  , "/fonts/**"
        		                  , "/js/**"
        		                  , "/img/**"
        		                  , "/favicon.ico"
        		                  , "/startbootstrap-sb-admin-2-gh-pages/**"
        		                  , "/error**"
        		                  );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 로그인 설정
        http.authorizeRequests()
            // ROLE_USER, ROLE_ADMIN으로 권한 분리 유알엘 정의
            .antMatchers("/", "/login", "/loginForm", "/admin/user/login", "/admin/user/loginForm").permitAll()
            .antMatchers("/admin/**").access("ROLE_ADMIN")
            .antMatchers("/**").access("ROLE_USER")
            .anyRequest().authenticated()
        .and()
            // 로그인 페이지 및 성공 url, handler 그리고 로그인 시 사용되는 id, password 파라미터 정의
            .formLogin()
            .loginPage("/loginForm")
            .loginProcessingUrl("/login")
            .loginProcessingUrl("/admin/user/login")
            .defaultSuccessUrl("/")
            .failureHandler(authFailureHandler)
            .successHandler(authSuccessHandler).defaultSuccessUrl("/startbootstrap-sb-admin-2-gh-pages/index.html")
            .usernameParameter("email")
            .passwordParameter("token")
        .and()
            .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/")
            .invalidateHttpSession(true)
        .and()
            .csrf()
        .and()
            .authenticationProvider(authProvider);
    }
}
