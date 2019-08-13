package pe.com.gadolfolozano.firebasefacebooklogin.model;

public class RegisterResponseModel {
    private boolean sucessfull;
    private String errorMessage;

    public RegisterResponseModel() {
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
