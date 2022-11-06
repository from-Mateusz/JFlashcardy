package cz.mateusz.flashcardy.security;

import cz.mateusz.flashcardy.exception.FailedAuthenticationException;
import cz.mateusz.flashcardy.web.data.modification.PlayerCredentialsData;
import org.springframework.security.core.Authentication;

public interface AuthenticationManager<T extends Authentication> {
    T authenticate(PlayerCredentialsData playerCredentialsData) throws FailedAuthenticationException;
}
