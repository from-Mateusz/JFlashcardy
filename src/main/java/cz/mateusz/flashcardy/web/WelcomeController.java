package cz.mateusz.flashcardy.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class WelcomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger("WelcomeControllerLogger");

    @PostConstruct
    private void doAfterComingToLife() {
        LOGGER.info("Welcome controller has come to live!");
    }
}
