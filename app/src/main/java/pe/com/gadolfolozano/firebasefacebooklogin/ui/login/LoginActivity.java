package pe.com.gadolfolozano.firebasefacebooklogin.ui.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;

import javax.inject.Inject;

import pe.com.gadolfolozano.firebasefacebooklogin.BR;
import pe.com.gadolfolozano.firebasefacebooklogin.MainActivity;
import pe.com.gadolfolozano.firebasefacebooklogin.R;
import pe.com.gadolfolozano.firebasefacebooklogin.base.BaseActivity;
import pe.com.gadolfolozano.firebasefacebooklogin.databinding.ActivityLoginBinding;
import pe.com.gadolfolozano.firebasefacebooklogin.model.LoginResponseModel;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {
    private CallbackManager callbackManager;

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

        viewModel.getLoginClick().observe(this, this::observeLoginClick);
        viewModel.getLoading().observe(this, this::observeLoading);

        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                viewModel.validateAccessToken(loginResult.getAccessToken()).observe(LoginActivity.this, LoginActivity.this::observeLoginResponse);
            }

            @Override
            public void onCancel() {
                showAlert(
                        getString(R.string.text_alert_error_login_facebook_title),
                        getString(R.string.text_alert_error_login_facebook_cancelled_message),
                        getString(R.string.text_alert_error_login_button));
            }

            @Override
            public void onError(FacebookException error) {
                showAlert(
                        getString(R.string.text_alert_error_login_facebook_title),
                        error.getMessage(),
                        getString(R.string.text_alert_error_login_button));
            }
        });
    }

    private void observeLoginResponse(LoginResponseModel loginResponseModel) {
        if (loginResponseModel != null) {
            viewModel.getLoading().setValue(false);
            if (loginResponseModel.isSucessfull()) {
                startActivity(new Intent(this, MainActivity.class));
            } else {
                showAlert(
                        getString(R.string.text_alert_error_login_firebase_title),
                        loginResponseModel.getErrorMessage(),
                        getString(R.string.text_alert_error_login_button));
            }
        }
    }

    private void observeLoading(Boolean loading) {
        if (loading != null) {
            if (loading) {
                showLoading();
            } else {
                hideLoading();
            }
        }
    }

    private void observeLoginClick(Boolean shouldClickLogin) {
        if (shouldClickLogin != null && shouldClickLogin) {
            LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile", "user_friends"));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
