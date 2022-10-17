package cz.mateusz.flashcardy.web.mappers;

import cz.mateusz.flashcardy.model.*;
import cz.mateusz.flashcardy.security.PasswordEncoder;
import cz.mateusz.flashcardy.web.data.NewPlayerData;
import org.springframework.stereotype.Component;

@Component
public class NewPlayerDataPlayerMapper extends BackwardDataModelMapper<NewPlayerData, Player> {

    private PasswordEncoder passwordEncoder;

    public NewPlayerDataPlayerMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Player from(NewPlayerData source) throws DataModelMapperException {
        try {
            Password plainPassword = Password.unsecured(source.getPassword());
            Player player = new Player(source.getName(),
                                        new Email(source.getEmail()),
                                        Password.secured(passwordEncoder.encode(plainPassword.getSecret())));
            return player;
        } catch (IncorrectEmailAddressException | UnsafePasswordException ex) {
            throw new DataModelMapperException(ex);
        }
    }
}
