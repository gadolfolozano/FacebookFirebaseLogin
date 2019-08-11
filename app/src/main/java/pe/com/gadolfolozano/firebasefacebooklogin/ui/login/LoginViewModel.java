package pe.com.gadolfolozano.firebasefacebooklogin.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.facebook.AccessToken;

import javax.inject.Inject;

import pe.com.gadolfolozano.firebasefacebooklogin.data.DataManager;
import pe.com.gadolfolozano.firebasefacebooklogin.model.LoginResponseModel;

public class LoginViewModel extends ViewModel {
    private DataManager dataManager;

    private MutableLiveData<Boolean> loginClick;
    private MutableLiveData<Boolean> loading;

    @Inject
    public LoginViewModel(DataManager dataManager) {
        this.dataManager = dataManager;
        this.loginClick = new MutableLiveData<>();
        this.loginClick.setValue(false);
        this.loading = new MutableLiveData<>();
        this.loading.setValue(false);
    }

    public void requestFacebookLogin() {
        this.loginClick.setValue(true);
    }

    public LiveData<LoginResponseModel> validateAccessToken(AccessToken accessToken) {
        this.loading.setValue(true);
        return dataManager.validateAccessToken(accessToken);
    }

    public MutableLiveData<Boolean> getLoginClick() {
        return loginClick;
    }

    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }
}
