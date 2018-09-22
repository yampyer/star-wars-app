package com.jeanpigomez.starwarsapp.ui.characters;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.jeanpigomez.starwarsapp.R;
import com.jeanpigomez.starwarsapp.data.model.Character;
import com.jeanpigomez.starwarsapp.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CharactersActivity extends BaseActivity implements CharactersContract.View {
    @BindView(R.id.rv_characters)
    RecyclerView charactersRecyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.text_notification)
    TextView notificationText;

    private CharacterAdapter adapter;
    @Inject
    CharactersPresenter presenter;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters_list);
        ButterKnife.bind(this);
        initializePresenter();
        setTitle(getString(R.string.app_name));
        setupWidgets();
    }

    private void initializePresenter() {
        DaggerCharactersComponent.builder()
                .charactersPresenterModule(new CharactersPresenterModule(this))
                .characterRepositoryComponent(getCharacterRepositoryComponent())
                .build()
                .inject(this);
    }

    private void setupWidgets() {
        // Setup recycler view
        adapter = new CharacterAdapter(new ArrayList<>());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        charactersRecyclerView.setLayoutManager(layoutManager);
        charactersRecyclerView.setAdapter(adapter);
        charactersRecyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter.setOnItemClickListener(
                (view, position) -> presenter.getCharacter(adapter.getItem(position).getName()));

        // Refresh layout
        refreshLayout.setOnRefreshListener(() -> presenter.loadCharacters(true));
        // Set notification text visible first
        notificationText.setVisibility(View.GONE);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.characters, menu);

        // Setup search widget in action bar
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setQueryHint(getString(R.string.search_hint));
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override public boolean onQueryTextChange(String newText) {
                presenter.search(newText);
                return true;
            }
        });

        return true;
    }

    @Override public void showCharacters(List<Character> characters) {
        notificationText.setVisibility(View.GONE);
        adapter.replaceData(characters);
    }

    @Override public void showNoDataMessage() {
        showNotification(getString(R.string.msg_no_data));
    }

    @Override public void showErrorMessage(String error) {
        showNotification(error);
    }

    @Override public void clearCharacters() {
        adapter.clearData();
    }

    @Override public void stopLoadingIndicator() {
        if (refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
    }

    @Override public void showCharacterDetail(Character character) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(character.getHomeworldURI()));
        startActivity(intent);
    }

    @Override public void showEmptySearchResult() {
        showNotification(getString(R.string.msg_empty_search_result));
    }

    private void showNotification(String message) {
        notificationText.setVisibility(View.VISIBLE);
        notificationText.setText(message);
    }
}
