package cz.mateusz.flashcardy.web.validators;

import cz.mateusz.flashcardy.players.model.Email;
import cz.mateusz.flashcardy.players.model.Password;
import cz.mateusz.flashcardy.translations.ContentTranslator;
import cz.mateusz.flashcardy.web.data.modification.NewPlayerJoiningData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class PlayerJoiningValidator extends AbstractDataValidator<NewPlayerJoiningData> {

    @Autowired
    public PlayerJoiningValidator(ContentTranslator contentTranslator) {
        super(contentTranslator);
    }

    @Override
    public List<DataValidationError> validate(NewPlayerJoiningData data, Locale locale) {
        List<DataValidationError> errors = new ArrayList<>();
        if(!Email.isAddressCorrect(data.getEmail().getValue())) {
            errors.add(getFormattedValidationError("player_invalid_" + data.getEmail().getProperty(), locale));
        }
        if(!Password.isSafe(data.getPassword().getValue())) {
            errors.add(getFormattedValidationError("player_weak_" + data.getPassword().getProperty(), locale));
        }

        return errors;
    }
}
