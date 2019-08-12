package pe.com.gadolfolozano.firebasefacebooklogin.data.firebase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.facebook.AccessToken;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import pe.com.gadolfolozano.firebasefacebooklogin.model.LoginResponseModel;

@Singleton
public class FirebaseHelperImplements implements FirebaseHelper {

    private FirebaseAuth auth;
    private FirebaseFirestore db;

    @Inject
    public FirebaseHelperImplements() {
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public LiveData<LoginResponseModel> validateAccessToken(AccessToken accessToken) {
        MutableLiveData<LoginResponseModel> loginResponse = new MutableLiveData<>();
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        auth.signInWithCredential(credential).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                saveUser(loginResponse);
            } else if (task.getException() != null) {
                LoginResponseModel response = new LoginResponseModel();
                response.setErrorMessage(task.getException().getMessage());
                loginResponse.setValue(response);
            }
        });
        return loginResponse;
    }

    private void saveUser(MutableLiveData<LoginResponseModel> loginResponse){
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null){
            Map<String, Object> user = new HashMap<>();
            user.put("id", currentUser.getUid());

            db.collection("users").add(user)
                    .addOnSuccessListener(documentReference -> {
                        LoginResponseModel response = new LoginResponseModel();
                        response.setSucessfull(true);
                        loginResponse.setValue(response);
                    })
                    .addOnFailureListener(e -> {
                        LoginResponseModel response = new LoginResponseModel();
                        response.setSucessfull(false);
                        response.setErrorMessage(e.getMessage());
                        loginResponse.setValue(response);
                    });
        }
    }
}
