package cz.mateusz.flashcardy.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.mateusz.flashcardy.model.CredentialsManager;
import cz.mateusz.flashcardy.model.PlayerSeeker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
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

    @Bean
    public AuthenticationEventPublisher authenticationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        return new DefaultAuthenticationEventPublisher(applicationEventPublisher);
    }

    @Bean
    public PlayerAuthenticationProvider playerAuthenticationProvider(AuthenticationEventPublisher AuthenticationEventPublisher) {
        PlayerAuthenticationProvider authenticationProvider = new PlayerAuthenticationProvider(
                AuthenticationEventPublisher,
                playerSeeker,
                credentialsManager,
                jwtManager
        );
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain authenticationFilterChain(HttpSecurity http, PlayerAuthenticationProvider playerAuthenticationProvider) throws Exception {
        http.authorizeRequests( authorize -> authorize.mvcMatchers("/auth").permitAll()
                                                        .mvcMatchers("/auth**").hasRole("PLAYER"));

        http.formLogin().disable()
                .httpBasic().disable()
                .logout().disable();

        http.sessionManagement()
                .maximumSessions(1);

        http.addFilter(playerAuthenticationFilter(playerAuthenticationProvider));

        http.csrf().disable();

        return http.build();
    }

    public PlayerAuthenticationFilter playerAuthenticationFilter(PlayerAuthenticationProvider playerAuthenticationProvider) {
        PlayerAuthenticationFilter filter = new PlayerAuthenticationFilter(new ProviderManager(playerAuthenticationProvider),
                                                                            new ObjectMapper());
        filter.setAuthenticationSuccessHandler(new PlayerAuthenticationSuccessHandler());
        filter.setAuthenticationFailureHandler(new PlayerAuthenticationFailureHandler());
        filter.setFilterProcessesUrl("/auth");
        return filter;
    }
}
