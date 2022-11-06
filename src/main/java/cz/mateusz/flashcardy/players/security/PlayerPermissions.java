package cz.mateusz.flashcardy.players.security;

import cz.mateusz.flashcardy.players.model.Player;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;

@Document("players_permissions")
@CompoundIndex(unique = true, def = "{ 'player': 1, 'permission': 1 }")
public class PlayerPermissions {

    @DocumentReference
    @Field("player")
    private Player owner;

    @DocumentReference
    @Field("permission")
    private PlayerPermission permission;

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public PlayerPermission getPermission() {
        return permission;
    }

    public void setPermission(PlayerPermission permission) {
        this.permission = permission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerPermissions that = (PlayerPermissions) o;
        return Objects.equals(owner, that.owner) && Objects.equals(permission, that.permission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, permission);
    }
}
