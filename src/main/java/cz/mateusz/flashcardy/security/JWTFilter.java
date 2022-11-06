package cz.mateusz.flashcardy.security;

import cz.mateusz.flashcardy.exception.InvalidJwtException;
import cz.mateusz.flashcardy.exception.UnsetSecretKeyException;
import cz.mateusz.flashcardy.players.model.Player;
import cz.mateusz.flashcardy.players.PlayerSeeker;
import cz.mateusz.flashcardy.model.UnknownPlayerException;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private JWTManager jwtManager;

    private PlayerSeeker playerSeeker;

    public JWTFilter(JWTManager jwtManager, PlayerSeeker playerSeeker) {
        this.jwtManager = jwtManager;
        this.playerSeeker = playerSeeker;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(!(null != authHeader && !authHeader.isBlank() && authHeader.startsWith("Bearer"))) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            final Base64.Decoder b64Decoder = Base64.getDecoder();
            final String jwt = (authHeader.split(" ")[1]).trim();
            final Long playerId = Long.valueOf(new String(b64Decoder.decode(request.getHeader("pid"))));
            final Player player = playerSeeker.seekPlayerById(playerId);
            jwtManager.verify(jwt, player.getEmailAddress());

            final JWTAuthentication authentication = new JWTAuthentication(null, null,
                                                                        List.of(new SimpleGrantedAuthority("PLAYER")), jwt);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            securityContext.setAuthentication(authentication);
            SecurityContextHolder.setContext(securityContext);
            filterChain.doFilter(request, response);
        } catch (UnknownPlayerException | InvalidJwtException | UnsetSecretKeyException ex) {
            filterChain.doFilter(request, response);
            return;
        }
    }
}
