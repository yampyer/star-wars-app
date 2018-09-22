package com.jeanpigomez.starwarsapp.ui.base;

import android.arch.lifecycle.LifecycleRegistry;
import android.support.v7.app.AppCompatActivity;

import com.jeanpigomez.starwarsapp.AndroidApplication;
import com.jeanpigomez.starwarsapp.data.CharacterRepositoryComponent;

public class BaseActivity extends AppCompatActivity {
    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    protected CharacterRepositoryComponent getCharacterRepositoryComponent() {
        return ((AndroidApplication) getApplication()).getCharacterRepositoryComponent();
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }
}
