package cz.mateusz.flashcardy.model;

import cz.mateusz.flashcardy.data.PlayerRepository;
import cz.mateusz.flashcardy.security.PasswordEncoder;
import cz.mateusz.flashcardy.security.PasswordGenerator;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultCredentialsManager implements CredentialsManager {

    private PasswordGenerator generator;

    private PasswordEncoder passwordEncoder;

    private PlayerRepository playerRepository;

    public DefaultCredentialsManager(PasswordGenerator generator, PasswordEncoder passwordEncoder, PlayerRepository playerRepository) {
        this.generator = generator;
        this.passwordEncoder = passwordEncoder;
        this.playerRepository = playerRepository;
    }

    @Override
    public Password proposePassword() throws UnsafePasswordException {
        String proposedSecret = generator.generate();
        return Password.unsecured(proposedSecret);
    }

    @Override
    public void comparePasswords(Password plainPw, Password encodedPw) throws AsymmetricalPasswordException {
        if(!passwordEncoder.compare(plainPw, encodedPw)) throw new AsymmetricalPasswordException();
    }

    @Override
    public boolean changePassword(Long ownerId, Password newPassword) throws UnknownPlayerException {
        Optional<Player> possibleOwner = playerRepository.findById(ownerId);
        if(!possibleOwner.isPresent()) throw new UnknownPlayerException(ownerId);
        Player owner = possibleOwner.get();
        if(!newPassword.isSecured()) owner.changePassword(passwordEncoder.encode(newPassword.getSecret()));
        else owner.changePassword(newPassword);
        return true;
    }
}
