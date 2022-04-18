package com.csi.jcl.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

/**
 * SpringSecurity的配置
 *
 * @author si1206 Sam Chen
 * @version 1.8
 * @date 2021/08/17
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 注入DataSource
     *
     * @author si1206 Sam Chen
     * @version 1.8
     * @date 2021/08/17
     */
    private final DataSource dataSource;

    public WebSecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 從資料庫取得userid, userpw,enabled,authority
     *
     * @author si1206 Sam Chen
     * @version 1.8
     * @date 2021/08/17
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select userid, userpw, enabled from testuserinfo where userid = ? ")
                .authoritiesByUsernameQuery("select userid, authority from authorities where userid = ? ")
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     * Security頁面權限的設定
     *
     * @author si1206 Sam Chen
     * @version 1.8
     * @date 2021/08/17
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                // 全部角色可以瀏覽
                .antMatchers("jcl/**", "/code/image").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                // 對應LoginController的/loginPage
                .loginPage("/loginPage")
                // 對應自訂的登入action
                .loginProcessingUrl("/login/form")
                .permitAll()
                .and()
                .logout()
                .permitAll();

        //這邊是測試方便使用
//        http.authorizeRequests()
//                .anyRequest()
//                .permitAll();
    }
}
