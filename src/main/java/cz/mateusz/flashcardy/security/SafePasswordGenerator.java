package cz.mateusz.flashcardy.security;

import cz.mateusz.flashcardy.players.model.Password;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

@Service
public class SafePasswordGenerator implements PasswordGenerator {

    private static final SecureRandom RANDOMIZER = new SecureRandom();

    @Override
    public String generate() {
        char auxSpecialCharacters[] = new char[] { '%', '!', '#', '@', '+', 'a', 'z', 'g' , 'd', 'A', 'B' };
        byte secretBuffer[] = new byte[16];
        Random random = new Random();
        RANDOMIZER.nextBytes(secretBuffer);
        byte encodedSecretBuffer[] = Base64.getEncoder().encode(secretBuffer);
        // Needed to expel last two bytes, as they are "=" signs and change them into random aux characters consent to password pattern.
        // Otherwise, the all proposed passwords would have always had something in common (the pattern that is easy to notice).
        encodedSecretBuffer[encodedSecretBuffer.length -1] = (byte) auxSpecialCharacters[random.nextInt(auxSpecialCharacters.length)];
        encodedSecretBuffer[encodedSecretBuffer.length -2] = (byte) auxSpecialCharacters[random.nextInt(auxSpecialCharacters.length)];
        String secret = new String(encodedSecretBuffer);
        while(!Password.isSafe(secret)) {
            RANDOMIZER.nextBytes(secretBuffer);
            byte newEncodedSecretBuffer[] = Base64.getEncoder().encode(secretBuffer);
            secret = new String(newEncodedSecretBuffer);
        }
        return secret;
    }
}
