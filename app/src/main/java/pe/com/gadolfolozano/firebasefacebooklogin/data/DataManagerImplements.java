package pe.com.gadolfolozano.firebasefacebooklogin.data;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DataManagerImplements implements DataManager {

    private Context context;

    @Inject
    public DataManagerImplements(Context context){
        this.context = context;
    }
}
