package pe.com.gadolfolozano.firebasefacebooklogin.data.firebase;

import androidx.lifecycle.LiveData;

import com.facebook.AccessToken;

import pe.com.gadolfolozano.firebasefacebooklogin.model.LoginResponseModel;

public interface FirebaseHelper {
    LiveData<LoginResponseModel> validateAccessToken(AccessToken accessToken);
}
