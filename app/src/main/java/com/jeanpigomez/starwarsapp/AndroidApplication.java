package com.jeanpigomez.starwarsapp;

import android.app.Application;
import com.facebook.stetho.Stetho;
import com.jeanpigomez.starwarsapp.data.CharacterRepositoryComponent;
import com.jeanpigomez.starwarsapp.data.DaggerCharacterRepositoryComponent;
import com.squareup.leakcanary.LeakCanary;
import timber.log.Timber;

public class AndroidApplication extends Application {

    private CharacterRepositoryComponent repositoryComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initializeDependencies();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            Stetho.initializeWithDefaults(this);
        }

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }

    private void initializeDependencies() {
        repositoryComponent = DaggerCharacterRepositoryComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public CharacterRepositoryComponent getCharacterRepositoryComponent() {
        return repositoryComponent;
    }
}
