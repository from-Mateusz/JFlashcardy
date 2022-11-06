package cz.mateusz.flashcardy.web;

import cz.mateusz.flashcardy.players.PlayerWriter;
import cz.mateusz.flashcardy.players.model.Player;
import cz.mateusz.flashcardy.web.data.modification.NewPlayerJoiningData;
import cz.mateusz.flashcardy.web.data.modification.converters.NewPlayerJoiningDataConverter;
import cz.mateusz.flashcardy.web.data.presentation.PresentationStatementsFactory;
import cz.mateusz.flashcardy.web.data.presentation.Statement;
import cz.mateusz.flashcardy.web.validators.DataValidationError;
import cz.mateusz.flashcardy.web.validators.PlayerJoiningValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "/api/public/player")
public class JoinPlayerApi {

    @Autowired
    private PlayerWriter playerWriter;

    @Autowired
    private PlayerJoiningValidator playerJoiningValidator;

    @Autowired
    private NewPlayerJoiningDataConverter newPlayerJoiningDataConverter;

    @Autowired
    private PresentationStatementsFactory presentationStatementsFactory;

    @PostMapping(value= "/join")
    @ResponseBody
    public ObjectApiResponse joinPlayer(@RequestBody NewPlayerJoiningData playerJoiningData,
                                        HttpServletRequest request)
    {
        Locale locale = request.getAttribute("locale") == null ? Locale.ENGLISH : (Locale) request.getAttribute("locale");
        List<DataValidationError> errors = playerJoiningValidator.validate(playerJoiningData, locale);
        if(!errors.isEmpty()) return ObjectApiResponse.<Statement>create(HttpStatus.OK,
                                                                        presentationStatementsFactory.getFailStatement("player_unsuccessful_joining",
                                                                                                                        locale),
                                                                        errors);

        Player newPlayer = playerWriter.write(newPlayerJoiningDataConverter.convert(playerJoiningData));
        return ObjectApiResponse.<Statement>create(HttpStatus.OK,
                presentationStatementsFactory.getSuccessStatement("player_successful_joining", locale));
    }
}
