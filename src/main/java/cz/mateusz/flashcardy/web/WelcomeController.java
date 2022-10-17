package cz.mateusz.flashcardy.web;

import cz.mateusz.flashcardy.model.Explanation;
import cz.mateusz.flashcardy.model.Flashcard;
import cz.mateusz.flashcardy.model.Objective;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
