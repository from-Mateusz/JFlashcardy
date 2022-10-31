package cz.mateusz.flashcardy.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JWTAuthentication extends UsernamePasswordAuthenticationToken {

    private String token;

    public JWTAuthentication(Object principal, Object credentials, String token) {
        super(principal, credentials);
        this.token = token;
    }

    public JWTAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, String token) {
        super(principal, credentials, authorities);
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
