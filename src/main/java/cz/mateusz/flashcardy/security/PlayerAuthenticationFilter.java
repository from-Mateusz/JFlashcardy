package cz.mateusz.flashcardy.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class PlayerAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private ObjectMapper oMapper;

    public PlayerAuthenticationFilter(AuthenticationManager authenticationManager,
                                      ObjectMapper oMapper) {
        super(authenticationManager);
        this.oMapper = oMapper;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        super.doFilter(request, response, chain);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try(ServletInputStream is = request.getInputStream()) {
            PlayerCredentials playerCredentials = oMapper.readValue(request.getInputStream(), PlayerCredentials.class);
            return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(playerCredentials.email,
                                                            playerCredentials.password, Collections.emptyList()));
        } catch (JsonProcessingException ex) {
            throw new AuthenticationServiceException("");
        } catch (IOException ex) {
            throw new AuthenticationServiceException("");
        }
    }

    private static class PlayerCredentials {
        private String email;
        private String password;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

}
