package pe.com.gadolfolozano.firebasefacebooklogin.data.firebase;

import androidx.lifecycle.LiveData;

import com.facebook.AccessToken;

import pe.com.gadolfolozano.firebasefacebooklogin.model.LoginResponseModel;
import pe.com.gadolfolozano.firebasefacebooklogin.model.RegisterResponseModel;

public interface FirebaseHelper {
    LiveData<LoginResponseModel> validateAccessToken(AccessToken accessToken);

    LiveData<RegisterResponseModel> performSaveData(String name, String surname, String age, String birthDay);
}
