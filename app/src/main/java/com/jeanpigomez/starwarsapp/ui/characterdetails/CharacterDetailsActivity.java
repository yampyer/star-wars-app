package com.jeanpigomez.starwarsapp.ui.characterdetails;

import android.os.Bundle;
import android.widget.TextView;

import com.jeanpigomez.starwarsapp.R;
import com.jeanpigomez.starwarsapp.data.model.Character;
import com.jeanpigomez.starwarsapp.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CharacterDetailsActivity extends BaseActivity implements CharacterDetailsContract.View {
    @BindView(R.id.text_name)
    TextView tvName;

    @BindView(R.id.text_height)
    TextView tvHeight;

    @BindView(R.id.text_mass)
    TextView tvMass;

    @BindView(R.id.text_birthday)
    TextView tvBirthday;

    @BindView(R.id.text_gender)
    TextView tvGender;

    @BindView(R.id.text_homeworld)
    TextView tvHomeworld;

    @BindView(R.id.text_films)
    TextView tvFilms;

    @BindView(R.id.text_vehicles)
    TextView tvVehicles;

    @Inject
    CharacterDetailsPresenter presenter;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        initializePresenter();
        Character character = (Character) getIntent().getSerializableExtra("CHARACTER");
        presenter.formatCharacter(character);
        setTitle(character.getName());
    }

    private void initializePresenter() {
        DaggerCharacterDetailsComponent.builder()
                .characterDetailsPresenterModule(new CharacterDetailsPresenterModule(this))
                .build()
                .inject(this);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override public void showCharacterDetail(Character character) {
        tvName.setText(character.getName());
        tvHeight.setText(character.getHeight());
        tvMass.setText(character.getMass());
        tvBirthday.setText(character.getBirthYear());
        tvGender.setText(character.getGender());
        tvHomeworld.setText(character.getHomeworldURI());
        tvFilms.setText(presenter.formatArraylist(character.getFilmsURI()));
        tvVehicles.setText(presenter.formatArraylist(character.getVehiclesURI()));
    }
}
