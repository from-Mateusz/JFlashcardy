package cz.mateusz.flashcardy.web;

import cz.mateusz.flashcardy.web.data.DataField;
import cz.mateusz.flashcardy.web.data.PlayerAuthenticationResultData;
import cz.mateusz.flashcardy.web.data.modification.PlayerCredentialsData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthenticationControllerTests {

    @Test
    public void authenticationAttemptShouldFail(@Autowired TestRestTemplate restTemplate) throws Exception {
        PlayerCredentialsData playerCredentialsData = new PlayerCredentialsData();
        playerCredentialsData.setEmail(DataField.<String>of("emailAddress", "mateusz2@mateusz.com"));
        playerCredentialsData.setPassword(DataField.<String>of("password", "heLL@World99"));
//        ObjectMapper objMapper = new ObjectMapper();
//        byte[] body = objMapper.writeValueAsBytes(playerCredentialsData);
//        MvcResult reqResult = mockMvc.perform(post("/auth")
//                                    .contentType(MediaType.APPLICATION_JSON)
//                                    .content(body))
//                                    .andReturn();

        PlayerAuthenticationResultData authenticationResponse = restTemplate.postForObject("/auth",
                                                                                                playerCredentialsData,
                                                                                                PlayerAuthenticationResultData.class);

        assertThat(authenticationResponse.isAuthenticated(), is(false));
    }
}
