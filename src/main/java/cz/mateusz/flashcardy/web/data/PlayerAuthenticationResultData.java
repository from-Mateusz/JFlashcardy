package cz.mateusz.flashcardy.web.data;

public class PlayerAuthenticationResultData {

    private boolean authenticated;

    private String token;

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
