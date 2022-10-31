package cz.mateusz.flashcardy.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import cz.mateusz.flashcardy.exception.ErroneousProperty;
import cz.mateusz.flashcardy.exception.ExceptionFactory;
import cz.mateusz.flashcardy.exception.InvalidJwtException;
import cz.mateusz.flashcardy.exception.UnsetSecretKeyException;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class DefaultJWTManager implements JWTManager {

    private static final long TOKEN_LONGEVITY_IN_MS = 15 * (60 * 1000); // 15 minutes
    private Environment environment;

    private ExceptionFactory exceptionFactory;

    public DefaultJWTManager(Environment environment, ExceptionFactory exceptionFactory) {
        this.environment = environment;
        this.exceptionFactory = exceptionFactory;
    }

    @Override
    public String get(String owner) throws UnsetSecretKeyException {
        Algorithm algorithm = Algorithm.HMAC256(loadSecret());
        String token = JWT.create()
                            .withSubject(owner)
                            .withExpiresAt(Instant.now().plusMillis(TOKEN_LONGEVITY_IN_MS))
                            .sign(algorithm);
        return token;
    }

    @Override
    public void verify(String token, String owner) throws UnsetSecretKeyException, InvalidJwtException {
        Algorithm algorithm = Algorithm.HMAC256(loadSecret());
        JWTVerifier verifier = JWT.require(algorithm)
                                    .withSubject(owner)
                                    .build();

        try {
            DecodedJWT jwt = verifier.verify(token);
        } catch (JWTVerificationException ex) {
            Optional<InvalidJwtException> possibleFormattedEx = this.exceptionFactory.create(InvalidJwtException.class,
                                                                                            ErroneousProperty.of("token", token));
            if(possibleFormattedEx.isPresent()) throw possibleFormattedEx.get();
            else throw new InvalidJwtException();
        }
    }

    private String loadSecret() throws UnsetSecretKeyException {
        String secret = environment.getProperty("security.secret");
        if(secret == null || secret.isBlank()) throw new UnsetSecretKeyException();
        return secret;
    }
}
