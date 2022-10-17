package cz.mateusz.flashcardy.model;

import org.springframework.data.mongodb.core.index.Indexed;

public class Player extends DomainEntity {

    @Indexed(name = "player_name_index_asc")
    private String name;

    private Email email;

    private Password password;

    public Player(String name, Email email, Password password) {
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
}
