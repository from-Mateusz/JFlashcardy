package cz.mateusz.flashcardy.web;

import cz.mateusz.flashcardy.exception.FailedAuthenticationException;
import cz.mateusz.flashcardy.security.JWTAuthentication;
import cz.mateusz.flashcardy.security.JWTAuthenticationManager;
import cz.mateusz.flashcardy.web.data.PlayerAuthenticationResultData;
import cz.mateusz.flashcardy.web.data.modification.PlayerCredentialsData;
import cz.mateusz.flashcardy.web.validators.PlayerCredentialsValidator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@RestController
public class AuthenticationApi {

    private JWTAuthenticationManager jwtAuthenticationManager;

    private PlayerCredentialsValidator playerCredentialsValidator;

    public AuthenticationApi(JWTAuthenticationManager jwtAuthenticationManager,
                             PlayerCredentialsValidator playerCredentialsValidator) {
        this.jwtAuthenticationManager = jwtAuthenticationManager;
        this.playerCredentialsValidator = playerCredentialsValidator;
    }

    @PostMapping("/api/public/auth")
    @ResponseBody
    public ObjectApiResponse authenticationResultData(@RequestBody PlayerCredentialsData credentialsData,
                                                      HttpServletRequest request) throws FailedAuthenticationException {
        Locale locale = request.getAttribute("locale") == null ? Locale.ENGLISH : (Locale) request.getAttribute("locale");
        ObjectApiResponse<PlayerAuthenticationResultData> apiResponse = new ObjectApiResponse();
        apiResponse.setErrors(playerCredentialsValidator.validate(credentialsData, locale));

        if(apiResponse.countErrors() > 0) return apiResponse;

        JWTAuthentication authentication = jwtAuthenticationManager.authenticate(credentialsData);
        PlayerAuthenticationResultData authenticationResultData = new PlayerAuthenticationResultData();
        if(authentication != null && authentication.isAuthenticated() && authentication.getToken() != null) {
            authenticationResultData.setAuthenticated(true);
            authenticationResultData.setToken(((JWTAuthentication)authentication).getToken());
        }

        apiResponse.setContent(authenticationResultData);

        return apiResponse;
    }
}
