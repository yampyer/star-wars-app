package com.jeanpigomez.starwarsapp.data;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.jeanpigomez.starwarsapp.data.database.CharacterDao;
import com.jeanpigomez.starwarsapp.data.database.StarWarsDb;

import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;

@Module
public class DatabaseModule {
    private static final String DATABASE = "database_name";

    @Provides
    @Named(DATABASE)
    String provideDatabaseName() {
        return Config.DATABASE_NAME;
    }

    @Provides
    @Singleton
    StarWarsDb provideStarWarsDao(Context context, @Named(DATABASE) String databaseName) {
        return Room.databaseBuilder(context, StarWarsDb.class, databaseName).build();
    }

    @Provides
    @Singleton
    CharacterDao provideCharacterDao(StarWarsDb starWarsDb) {
        return starWarsDb.characterDao();
    }
}
