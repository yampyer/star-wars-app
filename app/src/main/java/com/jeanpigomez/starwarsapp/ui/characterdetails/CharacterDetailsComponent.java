package com.jeanpigomez.starwarsapp.ui.characterdetails;

import com.jeanpigomez.starwarsapp.ui.base.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = {CharacterDetailsPresenterModule.class})

public interface CharacterDetailsComponent {
    void inject(CharacterDetailsActivity characterDetailsActivity);
}
