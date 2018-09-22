package com.jeanpigomez.starwarsapp.data.repository;

import android.support.annotation.VisibleForTesting;

import com.jeanpigomez.starwarsapp.data.model.Character;

import io.reactivex.Flowable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class CharacterRepository implements CharacterDataSource {

    private CharacterDataSource remoteDataSource;
    private CharacterDataSource localDataSource;

    @VisibleForTesting List<Character> caches;

    @Inject public CharacterRepository(@Local CharacterDataSource localDataSource,
                                      @Remote CharacterDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;

        caches = new ArrayList<>();
    }

    @Override public Flowable<List<Character>> loadCharacters(boolean forceRemote) {
        if (forceRemote) {
            return refreshData();
        } else {
            if (caches.size() > 0) {
                // if cache is available, return it immediately
                return Flowable.just(caches);
            } else {
                // else return data from local storage
                return localDataSource.loadCharacters(false)
                        .take(1)
                        .flatMap(Flowable::fromIterable)
                        .doOnNext(character -> caches.add(character))
                        .toList()
                        .toFlowable()
                        .filter(list -> !list.isEmpty())
                        .switchIfEmpty(
                                refreshData()); // If local data is empty, fetch from remote source instead.
            }
        }
    }

    /**
     * Fetches data from remote source.
     * Save it into both local database and cache.
     *
     * @return the Flowable of newly fetched data.
     */
    Flowable<List<Character>> refreshData() {
        return remoteDataSource.loadCharacters(true).doOnNext(list -> {
            // Clear cache
            caches.clear();
            // Clear data in local storage
            localDataSource.clearData();
        }).flatMap(Flowable::fromIterable).doOnNext(character -> {
            caches.add(character);
            localDataSource.addCharacter(character);
        }).toList().toFlowable();
    }

    /**
     * Loads a character by its name.
     *
     * @param characterName character's name.
     * @return a corresponding question from cache.
     */
    public Flowable<Character> getCharacter(String characterName) {
        return Flowable.fromIterable(caches).filter(character -> character.getName() == characterName);
    }

    @Override public void addCharacter(Character character) {
        //Currently, we do not need this.
        throw new UnsupportedOperationException("Unsupported operation");
    }

    @Override public void clearData() {
        caches.clear();
        localDataSource.clearData();
    }
}
