package com.jeanpigomez.starwarsapp.data;

import com.jeanpigomez.starwarsapp.AppModule;
import com.jeanpigomez.starwarsapp.data.repository.CharacterRepository;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {CharacterRepositoryModule.class, AppModule.class, ApiServiceModule.class,
        DatabaseModule.class})
public interface CharacterRepositoryComponent {
    CharacterRepository provideCharacterRepository();
}
