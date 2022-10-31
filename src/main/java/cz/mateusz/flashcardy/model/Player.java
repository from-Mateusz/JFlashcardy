package cz.mateusz.flashcardy.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import javax.management.relation.Role;
import java.util.*;

@Document("players")
public class Player extends DomainEntity {

    @Indexed(name = "player_name_index_asc")
    private String name;

    private Email email;

    private Password password;

    @DocumentReference(lazy = true)
    private Set<PlayerPermission> permissions;

    Player() {
        this.permissions = new HashSet<>();
    }

    public Player(String name, Email email, Password password) {
        this();
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Password getPassword() {
        return password;
    }

    public void changeEmail(Email email) {
        this.email = email;
    }

    public void changePassword(Password password) {
        this.password = password;
    }

    public Email getEmail() {
        return email;
    }

    public String getEmailAddress() {
        return email.getAddress();
    }

    public Set<PlayerPermission> getPermissions() {
        return Collections.unmodifiableSet(this.permissions);
    }

    public boolean gainPermission(PlayerPermission permission) {
        return this.permissions.add(permission);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) && Objects.equals(email, player.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }
}
