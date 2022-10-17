package cz.mateusz.flashcardy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(
        exclude = { DataSourceAutoConfiguration.class,
                    SecurityAutoConfiguration.class }
)
public class FlashcardyApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger("FlashcardyApplicationLogger");

    public static void main(String...args) {
        SpringApplication.run(FlashcardyApplication.class, args);
        LOGGER.info("Flashcardy Application has come to live");
    }
}
