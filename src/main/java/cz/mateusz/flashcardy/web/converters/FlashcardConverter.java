package cz.mateusz.flashcardy.web.converters;

import cz.mateusz.flashcardy.model.Flashcard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class FlashcardConverter extends AbstractHttpMessageConverter<Flashcard> {

    private static final Logger LOGGER = LoggerFactory.getLogger("FlashcardRequestResponseConverterLogger");

    @Override
    protected boolean supports(Class<?> clazz) {
        return Flashcard.class.isAssignableFrom(clazz);
    }

    @Override
    protected Flashcard readInternal(Class<? extends Flashcard> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    protected void writeInternal(Flashcard flashcard, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        OutputStream os = outputMessage.getBody();
        String body = "{ Flashcard: { objective: " + flashcard.getObjective().getContent() + " } }";
        os.write(body.getBytes(StandardCharsets.UTF_8));
        os.close();
    }
}
