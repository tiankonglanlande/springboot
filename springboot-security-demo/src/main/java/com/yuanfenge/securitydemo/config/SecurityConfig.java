package com.yuanfenge.securitydemo.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joe Grandja
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorize -> authorize
                        /*配置放行资源或页面*/
                        .antMatchers("/css/**","/js/**", "/index").permitAll()
                        /*配置认证资源，必需是USER角色*/
                        .antMatchers("/user/**")
                        .hasRole("USER")
                )
                .formLogin(formLogin -> formLogin
                        //配置自定登录页面，不配置则为security默认登录页面
                        .loginPage("/login")
                        .failureUrl("/login-error")
                );
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // TODO 模拟数据库获取用户数据
        List dbUserList = new ArrayList();

        UserDetails aa = User.withDefaultPasswordEncoder()//设置密码为默认编码
                .username("aa")
                .password("aa")
                .roles("USER")
                .build();
        UserDetails bb = User.withDefaultPasswordEncoder()
                .username("bb")
                .password("bb")
                .roles("admin", "user")
                .build();
        dbUserList.add(aa);
        dbUserList.add(bb);

        /*将登录信息保存到缓存*/
        //return new InMemoryUserDetailsManager(aa,bb);
        return new InMemoryUserDetailsManager(dbUserList);
    }
}
