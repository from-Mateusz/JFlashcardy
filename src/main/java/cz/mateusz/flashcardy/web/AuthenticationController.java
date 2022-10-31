package cz.mateusz.flashcardy.web;

import cz.mateusz.flashcardy.security.JWTAuthentication;
import cz.mateusz.flashcardy.web.data.PlayerAuthenticationResultData;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    @GetMapping("/authenticated")
    @ResponseBody
    public PlayerAuthenticationResultData authenticationResultData(Authentication authentication) {
        PlayerAuthenticationResultData authenticationResultData = new PlayerAuthenticationResultData();
        if(authentication != null && authentication.isAuthenticated() && ((JWTAuthentication)authentication).getToken() != null) {
            authenticationResultData.setAuthenticated(true);
            authenticationResultData.setToken(((JWTAuthentication)authentication).getToken());
        }
        return authenticationResultData;
    }
}
