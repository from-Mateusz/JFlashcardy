package cz.mateusz.flashcardy.security;

import cz.mateusz.flashcardy.players.PlayerSeeker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

    @Autowired
    private PlayerSeeker playerSeeker;

    @Autowired
    private CredentialsManager credentialsManager;

    @Autowired
    private JWTManager jwtManager;

    @Autowired
    private JWTFilter jwtFilter;

    @Bean
    public SecurityFilterChain authenticationFilterChain(HttpSecurity http) throws Exception {
        http.formLogin().disable()
                .httpBasic().disable()
                .logout().disable();

        http.authorizeRequests(authorize -> {
            authorize.mvcMatchers("/api/public/**").permitAll();
            authorize.mvcMatchers("/api/private/**").hasRole("PLAYER");
            authorize.anyRequest().authenticated();
        });

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        http.csrf().disable();

        return http.build();
    }
}
