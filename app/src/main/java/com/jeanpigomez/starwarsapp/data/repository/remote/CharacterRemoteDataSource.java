package com.jeanpigomez.starwarsapp.data.repository.remote;

import com.jeanpigomez.starwarsapp.data.api.PeopleResponse;
import com.jeanpigomez.starwarsapp.data.api.PeopleService;
import com.jeanpigomez.starwarsapp.data.model.Character;
import com.jeanpigomez.starwarsapp.data.repository.CharacterDataSource;

import io.reactivex.Flowable;
import java.util.List;
import javax.inject.Inject;

public class CharacterRemoteDataSource implements CharacterDataSource {
    private PeopleService peopleService;

    @Inject
    public CharacterRemoteDataSource(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public Flowable<List<Character>> loadCharacters(boolean forceRemote) {
        return peopleService.loadCharacters(1).map(PeopleResponse::getCharacters);
    }

    @Override
    public void addCharacter(Character character) {
        //Currently, we do not need this for remote source.
        throw new UnsupportedOperationException("Unsupported operation");
    }

    @Override
    public void clearData() {
        //Currently, we do not need this for remote source.
        throw new UnsupportedOperationException("Unsupported operation");
    }
}
