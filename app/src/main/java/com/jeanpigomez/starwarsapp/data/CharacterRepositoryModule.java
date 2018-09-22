package com.jeanpigomez.starwarsapp.data;

import com.jeanpigomez.starwarsapp.data.repository.CharacterDataSource;
import com.jeanpigomez.starwarsapp.data.repository.Local;
import com.jeanpigomez.starwarsapp.data.repository.Remote;
import com.jeanpigomez.starwarsapp.data.repository.local.CharacterLocalDataSource;
import com.jeanpigomez.starwarsapp.data.repository.remote.CharacterRemoteDataSource;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class CharacterRepositoryModule {

    @Provides
    @Local
    @Singleton
    public CharacterDataSource provideLocalDataSource(CharacterLocalDataSource characterLocalDataSource) {
        return characterLocalDataSource;
    }

    @Provides
    @Remote
    @Singleton
    public CharacterDataSource provideRemoteDataSource(CharacterRemoteDataSource characterRemoteDataSource) {
        return characterRemoteDataSource;
    }

}
