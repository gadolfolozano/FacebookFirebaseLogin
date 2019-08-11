package pe.com.gadolfolozano.firebasefacebooklogin.data.firebase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.facebook.AccessToken;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;
import javax.inject.Singleton;

import pe.com.gadolfolozano.firebasefacebooklogin.model.LoginResponseModel;

@Singleton
public class FirebaseHelperImplements implements FirebaseHelper {

    private FirebaseAuth auth;

    @Inject
    public FirebaseHelperImplements() {
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public LiveData<LoginResponseModel> validateAccessToken(AccessToken accessToken) {
        MutableLiveData<LoginResponseModel> loginResponse = new MutableLiveData<>();
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        auth.signInWithCredential(credential).addOnCompleteListener(task -> {
            LoginResponseModel response = new LoginResponseModel();
            response.setSucessfull(task.isSuccessful());
            if (task.getException() != null) {
                response.setErrorMessage(task.getException().getMessage());
            }
            loginResponse.setValue(response);
        });
        return loginResponse;
    }
}
