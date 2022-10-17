package cz.mateusz.flashcardy.web.mappers;

import cz.mateusz.flashcardy.model.Player;
import cz.mateusz.flashcardy.model.PlayerSeeker;
import cz.mateusz.flashcardy.web.data.PublicExistingPlayerData;
import org.springframework.stereotype.Component;

@Component
public class PlayerPublicExistingPlayerDataMapper extends ForwardDataModelMapper<Player, PublicExistingPlayerData> {

    @Override
    public PublicExistingPlayerData from(Player source) throws DataModelMapperException {
        PublicExistingPlayerData playerData = new PublicExistingPlayerData();
        playerData.setName(source.getName());
        return playerData;
    }
}
