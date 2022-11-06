package cz.mateusz.flashcardy.web.validators;

import cz.mateusz.flashcardy.players.model.Email;
import cz.mateusz.flashcardy.translations.ContentTranslator;
import cz.mateusz.flashcardy.web.data.modification.PlayerCredentialsData;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class PlayerCredentialsValidator extends AbstractDataValidator<PlayerCredentialsData> {

    public PlayerCredentialsValidator(ContentTranslator contentTranslator) {
        super(contentTranslator);
    }

    @Override
    public List<DataValidationError> validate(PlayerCredentialsData data, Locale locale) {
        final List<DataValidationError> errors = new ArrayList<>();
        final String language = data.getLanguage();
        if(!(data.getEmail().getValue() != null && Email.isAddressCorrect( (String)(data.getEmail().getValue()) ))) {
            errors.add(getFormattedValidationError("empty_" + data.getEmail().getProperty(), locale));
        }
        if(!(data.getPassword().getValue() != null && !((String)(data.getPassword().getValue())).isEmpty())) {
            errors.add(getFormattedValidationError("empty_" + data.getPassword().getProperty(), locale));
        }
        return errors;
    }
}
