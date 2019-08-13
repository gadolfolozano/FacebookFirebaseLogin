package pe.com.gadolfolozano.firebasefacebooklogin.data;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.facebook.AccessToken;

import javax.inject.Inject;
import javax.inject.Singleton;

import pe.com.gadolfolozano.firebasefacebooklogin.data.firebase.FirebaseHelper;
import pe.com.gadolfolozano.firebasefacebooklogin.model.LoginResponseModel;
import pe.com.gadolfolozano.firebasefacebooklogin.model.RegisterResponseModel;

@Singleton
public class DataManagerImplements implements DataManager {

    private Context context;

    private FirebaseHelper firebaseHelper;

    @Inject
    public DataManagerImplements(Context context,
                                 FirebaseHelper firebaseHelper) {
        this.context = context;
        this.firebaseHelper = firebaseHelper;
    }

    @Override
    public LiveData<LoginResponseModel> validateAccessToken(AccessToken accessToken) {
        return this.firebaseHelper.validateAccessToken(accessToken);
    }

    @Override
    public LiveData<RegisterResponseModel> performSaveData(String name, String surname, String age, String birthDay) {
        return this.firebaseHelper.performSaveData(name, surname, age, birthDay);
    }
}
