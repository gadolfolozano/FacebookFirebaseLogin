package pe.com.gadolfolozano.firebasefacebooklogin.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import pe.com.gadolfolozano.firebasefacebooklogin.ui.SplashActivity;

@Module
public abstract class ActivityBuilder {
    @ContributesAndroidInjector
    abstract SplashActivity bindSplashActivity();
}
