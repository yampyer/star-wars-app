package com.jeanpigomez.starwarsapp.data.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.jeanpigomez.starwarsapp.data.Config;
import com.jeanpigomez.starwarsapp.data.model.Character;

import io.reactivex.Flowable;

import java.util.List;

@Dao
public interface CharacterDao {
    @Query("SELECT * FROM " + Config.CHARACTER_TABLE_NAME)
    Flowable<List<Character>> getAllCharacters();

    @Query("SELECT * FROM " + Config.CHARACTER_TABLE_NAME + " WHERE name == :name")
    Flowable<Character> getCharacterByName(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Character character);

    @Query("DELETE FROM " + Config.CHARACTER_TABLE_NAME)
    void deleteAll();
}
