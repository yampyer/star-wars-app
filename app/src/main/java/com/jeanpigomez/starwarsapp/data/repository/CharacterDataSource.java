package com.jeanpigomez.starwarsapp.data.repository;

import com.jeanpigomez.starwarsapp.data.model.Character;

import io.reactivex.Flowable;

import java.util.List;

public interface CharacterDataSource {
    Flowable<List<Character>> loadCharacters(boolean forceRemote);

    void addCharacter(Character character);

    void clearData();
}
