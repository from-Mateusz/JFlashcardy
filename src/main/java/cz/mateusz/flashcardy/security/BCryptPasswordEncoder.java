package cz.mateusz.flashcardy.security;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class BCryptPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(String password) {
        String salt = BCrypt.gensalt(5);
        return BCrypt.hashpw(password, salt);
    }

    @Override
    public boolean compare(String plain, String encoded) {
        return BCrypt.checkpw(plain, encoded);
    }
}
