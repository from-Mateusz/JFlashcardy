package cz.mateusz.flashcardy.security;

import cz.mateusz.flashcardy.model.Password;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class BCryptPasswordEncoder implements PasswordEncoder {

    @Override
    public Password encode(String password) {
        String salt = BCrypt.gensalt(5);
        return Password.secured(BCrypt.hashpw(password, salt), salt);
    }

    @Override
    public Password encode(String password, String salt) {
        return Password.secured(BCrypt.hashpw(password, salt), salt);
    }

    @Override
    public boolean compare(Password plain, Password encoded) {
        return BCrypt.checkpw(plain.getSecret(), encoded.getSecret());
    }
}
