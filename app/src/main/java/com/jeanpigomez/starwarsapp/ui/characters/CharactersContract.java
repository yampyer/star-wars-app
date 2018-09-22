package com.jeanpigomez.starwarsapp.ui.characters;

import com.jeanpigomez.starwarsapp.data.model.Character;
import com.jeanpigomez.starwarsapp.ui.base.BasePresenter;

import java.util.List;

public interface CharactersContract {
    interface View {
        void showCharacters(List<Character> characters);

        void clearCharacters();

        void showNoDataMessage();

        void showErrorMessage(String error);

        void showCharacterDetail(Character character);

        void stopLoadingIndicator();

        void showEmptySearchResult();
    }

    interface Presenter extends BasePresenter<View> {
        void loadCharacters(boolean onlineRequired);

        void getCharacter(String characterName);

        void search(String query);
    }
}
