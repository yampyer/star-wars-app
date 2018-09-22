package com.jeanpigomez.starwarsapp.ui.characterdetails;

import com.jeanpigomez.starwarsapp.data.model.Character;
import com.jeanpigomez.starwarsapp.ui.base.BasePresenter;

import java.util.List;

public interface CharacterDetailsContract {
    interface View {
        void showCharacterDetail(Character character);
    }

    interface Presenter extends BasePresenter<View> {
        void formatCharacter(Character character);
    }
}
