package cz.mateusz.flashcardy.web.data;

public class NewPlayerCreatedData {

    private String email;

    private boolean awaitsConfirmation;

    public NewPlayerCreatedData(String email, boolean awaitsConfirmation) {
        this.email = email;
        this.awaitsConfirmation = awaitsConfirmation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAwaitsConfirmation() {
        return awaitsConfirmation;
    }

    public void setAwaitsConfirmation(boolean awaitsConfirmation) {
        this.awaitsConfirmation = awaitsConfirmation;
    }
}
