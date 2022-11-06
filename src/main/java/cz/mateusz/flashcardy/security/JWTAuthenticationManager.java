package cz.mateusz.flashcardy.security;

import cz.mateusz.flashcardy.exception.ExceptionFactory;
import cz.mateusz.flashcardy.exception.FailedAuthenticationException;
import cz.mateusz.flashcardy.exception.UnsetSecretKeyException;
import cz.mateusz.flashcardy.model.*;
import cz.mateusz.flashcardy.players.model.Password;
import cz.mateusz.flashcardy.players.model.Player;
import cz.mateusz.flashcardy.players.PlayerSeeker;
import cz.mateusz.flashcardy.web.data.modification.PlayerCredentialsData;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JWTAuthenticationManager implements AuthenticationManager<JWTAuthentication> {

    private JWTManager jwtManager;

    private PlayerSeeker playerSeeker;

    private CredentialsManager credentialsManager;

    private ExceptionFactory exceptionFactory;

    public JWTAuthenticationManager(JWTManager jwtManager, PlayerSeeker playerSeeker, CredentialsManager credentialsManager, ExceptionFactory exceptionFactory) {
        this.jwtManager = jwtManager;
        this.playerSeeker = playerSeeker;
        this.credentialsManager = credentialsManager;
        this.exceptionFactory = exceptionFactory;
    }

    @Override
    public JWTAuthentication authenticate(PlayerCredentialsData playerCredentialsData) throws FailedAuthenticationException {
        try {
            Player player = playerSeeker.seekPlayerByEmail((String) playerCredentialsData.getEmail().getValue());
            this.credentialsManager.comparePasswords(Password.unsecured((String) playerCredentialsData.getPassword().getValue()),
                                                    player.getPassword());
            String jwt = jwtManager.get(player.getEmailAddress());
            JWTAuthentication authentication = new JWTAuthentication(player.getEmailAddress(), "",
                                                            List.of(new SimpleGrantedAuthority("PLAYER")), jwt);
            return authentication;
        } catch (UnknownPlayerException | UnsafePasswordException | AsymmetricalPasswordException |
                 UnsetSecretKeyException ex) {
           Optional<FailedAuthenticationException> possibleFormattedException = exceptionFactory.create(FailedAuthenticationException.class);
           if(possibleFormattedException.isPresent()) throw possibleFormattedException.get();
           else throw new FailedAuthenticationException();
        }
    }
}
