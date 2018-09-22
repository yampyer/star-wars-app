package com.jeanpigomez.starwarsapp.data.repository.local;

import com.jeanpigomez.starwarsapp.data.database.CharacterDao;
import com.jeanpigomez.starwarsapp.data.model.Character;
import com.jeanpigomez.starwarsapp.data.repository.CharacterDataSource;

import io.reactivex.Flowable;

import java.util.List;

import javax.inject.Inject;

public class CharacterLocalDataSource implements CharacterDataSource {

    private CharacterDao characterDao;

    @Inject
    public CharacterLocalDataSource(CharacterDao characterDao) {
        this.characterDao = characterDao;
    }

    @Override
    public Flowable<List<Character>> loadCharacters(boolean forceRemote) {
        return characterDao.getAllCharacters();
    }

    @Override
    public void addCharacter(Character character) {
        // Insert new one
        characterDao.insert(character);
    }

    @Override
    public void clearData() {
        // Clear old data
        characterDao.deleteAll();
    }
}
