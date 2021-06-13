package ru.otus.spring.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
@Slf4j
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                // По умолчанию SecurityContext хранится в сессии
                // Это необходимо, чтобы он нигде не хранился
                // и данные приходили каждый раз с запросом
//                .sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS )
//                .and()
                .authorizeRequests().antMatchers("/").anonymous()
                .and()
                .authorizeRequests().antMatchers("/**").authenticated()
                .and()
                // Включает Form-based аутентификацию
                .formLogin()
                .successForwardUrl("/book");
    }

    @SuppressWarnings("deprecation")
    @Bean
    public PasswordEncoder passwordEncoder() {
        log.info("in method passwordEncoder() ");
        return NoOpPasswordEncoder.getInstance();
    }

//    @Autowired
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        log.info("in method configure(AuthenticationManagerBuilder auth)");
//        auth.inMemoryAuthentication()
//                .withUser("admin").password("admin").roles("ADMIN");
//    }

//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
////                .withDefaultSchema()
//                .withUser(User.withUsername("user").password("password").roles("USER"))
//                .withUser(User.withUsername("admin").password("password").roles("ADMIN"));
//    }


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select login, password_hash, 'true' from users " +
                                "where login=?")
                .authoritiesByUsernameQuery(
                        "select login, role, 'true' from users " +
                                "where login=?")
                .passwordEncoder(passwordEncoder());
    }
}
