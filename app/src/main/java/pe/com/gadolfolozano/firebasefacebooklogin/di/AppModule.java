package pe.com.gadolfolozano.firebasefacebooklogin.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pe.com.gadolfolozano.firebasefacebooklogin.data.DataManager;
import pe.com.gadolfolozano.firebasefacebooklogin.data.DataManagerImplements;

@Module
public class AppModule {
    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }


    @Provides
    @Singleton
    DataManager provideDataManager(DataManagerImplements dataManager) {
        return dataManager;
    }
}
