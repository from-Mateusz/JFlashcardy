package cz.mateusz.flashcardy.model;

import cz.mateusz.flashcardy.security.SafePasswordGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class PasswordManagerTests {

    private static CredentialsManager passwordManager;

//    @BeforeAll
//    public static void setup() {
//        passwordManager = new DefaultCredentialsManager(new SafePasswordGenerator());
//    }
//
//    @Test
//    public void shouldProposeAnyPassword() throws UnsafePasswordException {
//        Password password = passwordManager.propose();
//        System.out.println("Generated password: " + password.getSecret());
//        assertThat(password, notNullValue());
//    }
}
