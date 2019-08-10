package pe.com.gadolfolozano.firebasefacebooklogin.ui.login;

import android.os.Bundle;

import javax.inject.Inject;

import pe.com.gadolfolozano.firebasefacebooklogin.BR;
import pe.com.gadolfolozano.firebasefacebooklogin.R;
import pe.com.gadolfolozano.firebasefacebooklogin.base.BaseActivity;
import pe.com.gadolfolozano.firebasefacebooklogin.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {

    @Inject
    LoginViewModel viewModel;

    @Override
    public int getBindingVariable() {
        return BR.loginViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public LoginViewModel getViewModel() {
        return viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
