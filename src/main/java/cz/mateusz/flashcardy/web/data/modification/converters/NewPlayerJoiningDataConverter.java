package cz.mateusz.flashcardy.web.data.modification.converters;

import cz.mateusz.flashcardy.players.model.*;
import cz.mateusz.flashcardy.security.BCryptPasswordEncoder;
import cz.mateusz.flashcardy.web.data.modification.NewPlayerJoiningData;
import org.springframework.stereotype.Component;

@Component
public class NewPlayerJoiningDataConverter implements DataConverter<NewPlayerJoiningData, Player> {

    private BCryptPasswordEncoder passwordEncoder;

    public NewPlayerJoiningDataConverter(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Player convert(NewPlayerJoiningData data) {
        Password password = passwordEncoder.encode(data.getPassword().getValue());
        Player player = new Player(data.getName().getValue(),
                                    Email.createUnchecked(data.getEmail().getValue()),
                                    password);
        return player;
    }
}
