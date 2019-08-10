package pe.com.gadolfolozano.firebasefacebooklogin.ui.splash;

import android.content.Intent;
import android.os.Bundle;

import javax.inject.Inject;

import pe.com.gadolfolozano.firebasefacebooklogin.BR;
import pe.com.gadolfolozano.firebasefacebooklogin.R;
import pe.com.gadolfolozano.firebasefacebooklogin.base.BaseActivity;
import pe.com.gadolfolozano.firebasefacebooklogin.databinding.ActivitySplashBinding;
import pe.com.gadolfolozano.firebasefacebooklogin.ui.login.LoginActivity;

public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> {

    @Inject
    SplashViewModel viewModel;

    @Override
    public int getBindingVariable() {
        return BR.splashViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public SplashViewModel getViewModel() {
        return viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel.getNavigateToLogin().observe(this, this::observeNavigateToLogin);
        viewModel.init();
    }

    private void observeNavigateToLogin(Boolean shouldNavigate) {
        if(shouldNavigate != null && shouldNavigate){
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }
}
