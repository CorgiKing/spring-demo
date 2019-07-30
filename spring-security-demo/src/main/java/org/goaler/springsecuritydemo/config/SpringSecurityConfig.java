package org.goaler.springsecuritydemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                .withUser("user").password(new BCryptPasswordEncoder().encode("123456")).roles("USER");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/test/**").permitAll()
                    .antMatchers("/demo/**").authenticated()
                    .antMatchers("/admin/**", "/ADMIN/**").hasRole("ADMIN")//拥有"ROLE_ADMIN"角色可以访问
                    .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
                    .anyRequest().authenticated()//这个要放在最后不然上面的配置会失效,先配置的优先级高于后配置的
                    .and()
                .formLogin()
//                    .loginPage("/login").permitAll()
                    .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/index")//如果指定了logoutSuccessHandler就失效了
//                    .logoutSuccessHandler(logoutSuccessHandler)
//                    .addLogoutHandler(logoutHandler)
//                    .deleteCookies(cookieNamesToClear)
                    .and()
                ;
    }
}
