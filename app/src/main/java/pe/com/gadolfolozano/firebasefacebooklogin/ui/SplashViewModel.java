package pe.com.gadolfolozano.firebasefacebooklogin.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

public class SplashViewModel extends ViewModel {

    private MutableLiveData<Boolean> navigateToLogin;

    @Inject
    public SplashViewModel() {
        navigateToLogin = new MutableLiveData<>();
        navigateToLogin.setValue(false);
    }

    public void init() {
        navigateToLogin.setValue(true);
    }

    public MutableLiveData<Boolean> getNavigateToLogin() {
        return navigateToLogin;
    }
}
