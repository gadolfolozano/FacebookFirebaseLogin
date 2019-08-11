package pe.com.gadolfolozano.firebasefacebooklogin.ui.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import pe.com.gadolfolozano.firebasefacebooklogin.data.DataManager;

public class LoginViewModel extends ViewModel {
    private DataManager dataManager;

    private MutableLiveData<Boolean> loginClick;

    @Inject
    public LoginViewModel(DataManager dataManager) {
        this.dataManager = dataManager;
        this.loginClick = new MutableLiveData<>();
        this.loginClick.setValue(false);
    }

    public void requestFacebookLogin() {
        this.loginClick.setValue(true);
    }

    public MutableLiveData<Boolean> getLoginClick() {
        return loginClick;
    }
}
