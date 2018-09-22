package com.jeanpigomez.starwarsapp.ui.characterdetails;

import dagger.Module;
import dagger.Provides;

@Module
public class CharacterDetailsPresenterModule {
    private CharacterDetailsContract.View view;

    public CharacterDetailsPresenterModule(CharacterDetailsContract.View view) {
        this.view = view;
    }

    @Provides
    public CharacterDetailsContract.View provideView() {
        return view;
    }
}
