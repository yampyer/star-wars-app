package com.jeanpigomez.starwarsapp.ui.characterdetails;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

import com.jeanpigomez.starwarsapp.data.model.Character;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * A presenter with life-cycle aware.
 */
public class CharacterDetailsPresenter implements CharacterDetailsContract.Presenter, LifecycleObserver {

    private CharacterDetailsContract.View view;

    @Inject
    public CharacterDetailsPresenter(CharacterDetailsContract.View view) {
        this.view = view;

        // Initialize this presenter as a lifecycle-aware when a view is a lifecycle owner.
        if (view instanceof LifecycleOwner) {
            ((LifecycleOwner) view).getLifecycle().addObserver(this);
        }
    }

    @Override @OnLifecycleEvent(Lifecycle.Event.ON_RESUME) public void onAttach() {
    }

    @Override @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE) public void onDetach() {
        // Clean up any no-longer-use resources here
    }

    @Override public void formatCharacter(Character character) {
        view.showCharacterDetail(character);
    }

    public String formatArraylist(ArrayList<String> arrayList) {
        StringBuilder sb = new StringBuilder();
        for (String s : arrayList)
        {
            sb.append(s);
            sb.append("\n");
        }

        return sb.toString();
    }
}
