package com.jeanpigomez.starwarsapp.ui.characters;

import com.jeanpigomez.starwarsapp.data.CharacterRepositoryComponent;
import com.jeanpigomez.starwarsapp.ui.base.ActivityScope;
import com.jeanpigomez.starwarsapp.util.schedulers.SchedulerModule;

import dagger.Component;

@ActivityScope
@Component(modules = {CharactersPresenterModule.class, SchedulerModule.class}, dependencies = {
        CharacterRepositoryComponent.class
})
public interface CharactersComponent {
    void inject(CharactersActivity charactersActivity);
}
