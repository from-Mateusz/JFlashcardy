package cz.mateusz.flashcardy.data;

import cz.mateusz.flashcardy.FlashcardyApplication;
import cz.mateusz.flashcardy.players.data.PlayerRepository;
import cz.mateusz.flashcardy.players.model.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

@SpringBootTest(classes = FlashcardyApplication.class)
public class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    public void shouldQueryAllPlayers() {
        List<Player> allPlayers = playerRepository.findAll();
        assertThat(allPlayers, hasSize(0));
    }
}
