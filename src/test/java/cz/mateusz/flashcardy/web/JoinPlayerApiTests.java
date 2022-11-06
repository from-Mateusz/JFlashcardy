package cz.mateusz.flashcardy.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.mateusz.flashcardy.players.PlayerWriter;
import cz.mateusz.flashcardy.security.JWTFilter;
import cz.mateusz.flashcardy.web.data.DataField;
import cz.mateusz.flashcardy.web.data.modification.NewPlayerJoiningData;
import cz.mateusz.flashcardy.web.data.modification.converters.NewPlayerJoiningDataConverter;
import cz.mateusz.flashcardy.web.data.presentation.PresentationStatementsFactory;
import cz.mateusz.flashcardy.web.data.presentation.Statement;
import cz.mateusz.flashcardy.web.validators.DataValidationError;
import cz.mateusz.flashcardy.web.validators.PlayerJoiningValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = JoinPlayerApi.class,
            excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JWTFilter.class))
@AutoConfigureMockMvc(addFilters = false)
public class JoinPlayerApiTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerWriter playerWriter;

    @MockBean
    private PlayerJoiningValidator playerJoiningValidator;

    @MockBean
    private NewPlayerJoiningDataConverter newPlayerJoiningDataConverter;

    @MockBean
    private PresentationStatementsFactory presentationStatementsFactory;

    @Test
    public void everythingShouldBePreset() {
        assertThat(mockMvc, notNullValue());
    }

    @Test
    public void playerWithInvalidEmailOrPasswordShouldNotJoin() throws Exception {
        NewPlayerJoiningData playerJoiningData = new NewPlayerJoiningData();
        playerJoiningData.setName(DataField.<String>of("playerName", "Anonymous"));
        playerJoiningData.setEmail(DataField.<String>of("playerEmailAddress", "badPlayer@gmail"));
        playerJoiningData.setPassword(DataField.<String>of("playerPassword", "not_nice_password"));

        when(playerJoiningValidator.validate(playerJoiningData))
                .thenReturn(List.of(new DataValidationError("playerPassword", "password is not consent with our safety policy")));

        MvcResult result = mockMvc.perform(post("/api/public/player/join")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content(new ObjectMapper().writeValueAsString(playerJoiningData)))
                                            .andReturn();

        ObjectApiResponse<Statement> unsuccessfulResponse = new ObjectMapper().readValue(result.getResponse().getContentAsByteArray(), ObjectApiResponse.class);

        assertThat(unsuccessfulResponse.hasErrors(), is(true));
    }

    @Test
    public void playerWithValidEmailAndPasswordShouldJoin() throws Exception {
        NewPlayerJoiningData playerJoiningData = new NewPlayerJoiningData();
        playerJoiningData.setName(DataField.<String>of("playerName", "Anonymous"));
        playerJoiningData.setEmail(DataField.<String>of("playerEmailAddress", "goodPlayer@gmail.com"));
        playerJoiningData.setPassword(DataField.<String>of("playerPassword", "passwordMe92!"));

        when(playerJoiningValidator.validate(playerJoiningData)).thenReturn(List.of());

        MvcResult result = mockMvc.perform(post("/api/public/player/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(playerJoiningData)))
                .andReturn();

        ObjectApiResponse<Statement> successfulResponse = new ObjectMapper().readValue(result.getResponse().getContentAsByteArray(), ObjectApiResponse.class);

        assertThat(successfulResponse.hasErrors(), is(false));
    }
}
