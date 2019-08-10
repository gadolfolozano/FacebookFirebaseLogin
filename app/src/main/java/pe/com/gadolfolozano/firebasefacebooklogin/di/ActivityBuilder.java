package pe.com.gadolfolozano.firebasefacebooklogin.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import pe.com.gadolfolozano.firebasefacebooklogin.ui.login.LoginActivity;
import pe.com.gadolfolozano.firebasefacebooklogin.ui.splash.SplashActivity;

@Module
public abstract class ActivityBuilder {
    @ContributesAndroidInjector
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector
    abstract LoginActivity bindLoginActivity();
}
