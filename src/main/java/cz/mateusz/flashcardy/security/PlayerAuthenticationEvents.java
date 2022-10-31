package cz.mateusz.flashcardy.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class PlayerAuthenticationEvents {

    static final Logger LOGGER = LoggerFactory.getLogger("AuthenticationEvent" );

    public PlayerAuthenticationEvents() {
        LOGGER.info("Authentication events' listeners are ready");
    }

    @EventListener
    public void doOnSuccessfulAuthentication(AuthenticationSuccessEvent successEvent) {

    }

    @EventListener
    public void doOnFailedAuthentication(AbstractAuthenticationFailureEvent failureEvent) {
        Authentication authentication = failureEvent.getAuthentication();
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("They failed to authenticate themselves: ");
        messageBuilder.append(authentication.getPrincipal());
        LOGGER.info(messageBuilder.toString());
    }
}
