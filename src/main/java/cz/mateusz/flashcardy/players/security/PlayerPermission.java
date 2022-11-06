package cz.mateusz.flashcardy.players.security;

import cz.mateusz.flashcardy.model.DomainEntity;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("permissions")
public class PlayerPermission extends DomainEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
