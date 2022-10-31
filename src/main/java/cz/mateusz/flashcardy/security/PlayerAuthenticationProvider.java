package cz.mateusz.flashcardy.security;

import cz.mateusz.flashcardy.exception.UnsetSecretKeyException;
import cz.mateusz.flashcardy.model.*;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public class PlayerAuthenticationProvider implements AuthenticationProvider {

    private AuthenticationEventPublisher eventPublisher;
    private PlayerSeeker playerSeeker;

    private CredentialsManager credentialsManager;

    private JWTManager jwtManager;

    public PlayerAuthenticationProvider(AuthenticationEventPublisher eventPublisher, PlayerSeeker playerSeeker, CredentialsManager credentialsManager, JWTManager jwtManager) {
        this.eventPublisher = eventPublisher;
        this.playerSeeker = playerSeeker;
        this.credentialsManager = credentialsManager;
        this.jwtManager = jwtManager;
    }

    /**
     * The implementation of this method is not perfect, it's password's policy dependant and might require password update on user's side.
     * It means if the policy in question is about to change, user is to be informed about it in advance (fixed time).
     * This way, user will avoid authentication issues caused by her password breaking general password's policy.
     * Obviously the other way to bypass authentication issues is to reset the password, so it will correspond with a most recent password's policy.
     * @param authentication the authentication request object.
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        try {
            Player player = playerSeeker.seekPlayerByEmail(email);
            credentialsManager.comparePasswords(Password.unsecured(password), player.getPassword());
            String token = jwtManager.get(email);
            this.keepAuthenticated(player, token);
        } catch (UnknownPlayerException | UnsafePasswordException | AsymmetricalPasswordException |
                 UnsetSecretKeyException ex) {
            handleDifferentAuthenticationException(ex, authentication);
            throw new BadCredentialsException("Authentication Failure");
        }
        return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), List.of(new SimpleGrantedAuthority("PLAYER")));
    }

    private void handleDifferentAuthenticationException(Exception ex, Authentication auth) {
        eventPublisher.publishAuthenticationFailure(new BadCredentialsException("Authentication Failure", ex), auth);
    }

    /**
     * We assume that every player simply has role PLAYER. Further, in the process we ought to base on his/her permission set.
     * @param player
     * @param token
     */
    private void keepAuthenticated(Player player, String token) {
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        JWTAuthentication jwtAuthentication = new JWTAuthentication(player.getPassword(),
                                                                    "",
                                                                    List.of(new SimpleGrantedAuthority("PLAYER")),
                                                                    token);
        securityContext.setAuthentication(jwtAuthentication);
        SecurityContextHolder.setContext(securityContext);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
