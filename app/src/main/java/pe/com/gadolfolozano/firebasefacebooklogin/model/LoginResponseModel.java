package pe.com.gadolfolozano.firebasefacebooklogin.model;

public class LoginResponseModel {
    private boolean sucessfull;
    private String errorMessage;

    public LoginResponseModel() {
        //Do nothing
    }

    public boolean isSucessfull() {
        return sucessfull;
    }

    public void setSucessfull(boolean sucessfull) {
        this.sucessfull = sucessfull;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
