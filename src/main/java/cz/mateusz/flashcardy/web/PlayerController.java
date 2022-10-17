package cz.mateusz.flashcardy.web;

import cz.mateusz.flashcardy.model.*;
import cz.mateusz.flashcardy.web.data.ComplexOperationStatus;
import cz.mateusz.flashcardy.web.data.NewPlayerData;
import cz.mateusz.flashcardy.web.data.PublicExistingPlayerData;
import cz.mateusz.flashcardy.web.mappers.DataModelMapperException;
import cz.mateusz.flashcardy.web.mappers.NewPlayerDataPlayerMapper;
import cz.mateusz.flashcardy.web.mappers.PlayerPublicExistingPlayerDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerSeeker playerSeeker;

    @Autowired
    private PlayerWriter playerWriter;

    @Autowired
    private CredentialsManager credentialsManager;

    @Autowired
    private NewPlayerDataPlayerMapper playerDataPlayerMapper;

    @Autowired
    private PlayerPublicExistingPlayerDataMapper playerPublicExistingPlayerDataMapper;

    @GetMapping("/all")
    @ResponseBody
    public List<PublicExistingPlayerData> findAllPlayers() throws DataModelMapperException {
        List<Player> allPlayers = playerSeeker.seekAllPlayers();
        List<PublicExistingPlayerData> playersData = new ArrayList<>();
        for(Player player : allPlayers) {
            playersData.add(playerPublicExistingPlayerDataMapper.from(player));
        }
        return playersData;
    }

    @PostMapping("/create")
    @ResponseBody
    public ComplexOperationStatus createPlayer(@RequestBody NewPlayerData playerData) throws DataModelMapperException,
                                                                                            IncorrectEmailAddressException,
                                                                                            UnsafePasswordException,
                                                                                            UnknownPlayerException
    {
        Player player = playerDataPlayerMapper.from(playerData);
        Player createdPlayer = playerWriter.write(player);
        if(createdPlayer.getId() > 0) return ComplexOperationStatus.successful();
        else return ComplexOperationStatus.failure();
    }
}
