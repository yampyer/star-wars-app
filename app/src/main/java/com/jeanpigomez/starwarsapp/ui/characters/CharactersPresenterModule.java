package com.jeanpigomez.starwarsapp.ui.characters;

import dagger.Module;
import dagger.Provides;

@Module
public class CharactersPresenterModule {
    private CharactersContract.View view;

    public CharactersPresenterModule(CharactersContract.View view) {
        this.view = view;
    }

    @Provides
    public CharactersContract.View provideView() {
        return view;
    }
}
