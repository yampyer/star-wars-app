package com.jeanpigomez.starwarsapp.ui.characters;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

import com.jeanpigomez.starwarsapp.data.model.Character;
import com.jeanpigomez.starwarsapp.data.repository.CharacterRepository;
import com.jeanpigomez.starwarsapp.util.schedulers.RunOn;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import static com.jeanpigomez.starwarsapp.util.schedulers.SchedulerType.IO;
import static com.jeanpigomez.starwarsapp.util.schedulers.SchedulerType.UI;

/**
 * A presenter with life-cycle aware.
 */
public class CharactersPresenter implements CharactersContract.Presenter, LifecycleObserver {

    private CharacterRepository repository;

    private CharactersContract.View view;

    private Scheduler ioScheduler;
    private Scheduler uiScheduler;

    private CompositeDisposable disposeBag;

    @Inject
    public CharactersPresenter(CharacterRepository repository, CharactersContract.View view,
                              @RunOn(IO) Scheduler ioScheduler, @RunOn(UI) Scheduler uiScheduler) {
        this.repository = repository;
        this.view = view;
        this.ioScheduler = ioScheduler;
        this.uiScheduler = uiScheduler;

        // Initialize this presenter as a lifecycle-aware when a view is a lifecycle owner.
        if (view instanceof LifecycleOwner) {
            ((LifecycleOwner) view).getLifecycle().addObserver(this);
        }

        disposeBag = new CompositeDisposable();
    }

    @Override @OnLifecycleEvent(Lifecycle.Event.ON_RESUME) public void onAttach() {
        loadCharacters(false);
    }

    @Override @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE) public void onDetach() {
        // Clean up any no-longer-use resources here
        disposeBag.clear();
    }

    @Override public void loadCharacters(boolean onlineRequired) {
        // Clear old data on view
        view.clearCharacters();

        // Load new one and populate it into view
        Disposable disposable = repository.loadCharacters(onlineRequired)
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe(this::handleReturnedData, this::handleError, () -> view.stopLoadingIndicator());
        disposeBag.add(disposable);
    }

    @Override public void getCharacter(String characterName) {
        Disposable disposable = repository.getCharacter(characterName)
                .filter(character -> character != null)
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe(character -> view.showCharacterDetail(character));
        disposeBag.add(disposable);
    }

    @Override public void search(final String charactersName) {
        // Load new one and populate it into view
        Disposable disposable = repository.loadCharacters(false)
                .flatMap(Flowable::fromIterable)
                .filter(character -> character.getName() != null)
                .filter(character -> character.getName().toLowerCase().contains(charactersName.toLowerCase()))
                .toList()
                .toFlowable()
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe(characters -> {
                    if (characters.isEmpty()) {
                        // Clear old data in view
                        view.clearCharacters();
                        // Show notification
                        view.showEmptySearchResult();
                    } else {
                        // Update filtered data
                        view.showCharacters(characters);
                    }
                });

        disposeBag.add(disposable);
    }

    /**
     * Updates view after loading data is completed successfully.
     */
    private void handleReturnedData(List<Character> list) {
        view.stopLoadingIndicator();
        if (list != null && !list.isEmpty()) {
            view.showCharacters(list);
        } else {
            view.showNoDataMessage();
        }
    }

    /**
     * Updates view if there is an error after loading data from repository.
     */
    private void handleError(Throwable error) {
        view.stopLoadingIndicator();
        view.showErrorMessage(error.getLocalizedMessage());
    }
}
