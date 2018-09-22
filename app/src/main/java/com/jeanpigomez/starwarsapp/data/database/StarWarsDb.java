package com.jeanpigomez.starwarsapp.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.jeanpigomez.starwarsapp.data.Converters;
import com.jeanpigomez.starwarsapp.data.model.Character;

@Database(entities = Character.class, version = 1)
@TypeConverters({Converters.class})
public abstract class StarWarsDb extends RoomDatabase {

    public abstract CharacterDao characterDao();
}
