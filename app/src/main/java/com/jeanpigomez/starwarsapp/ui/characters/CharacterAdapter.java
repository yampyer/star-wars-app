package com.jeanpigomez.starwarsapp.ui.characters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jeanpigomez.starwarsapp.R;
import com.jeanpigomez.starwarsapp.data.model.Character;
import com.jeanpigomez.starwarsapp.ui.base.BaseRecyclerViewAdapter;

import java.security.InvalidParameterException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class CharacterAdapter extends BaseRecyclerViewAdapter<CharacterAdapter.CharacterViewHolder> {
    class CharacterViewHolder extends RecyclerView.ViewHolder {

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

        public CharacterViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private List<Character> characters;

    public CharacterAdapter(@NonNull List<Character> characters) {
        this.characters = characters;
    }

    @Override public CharacterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_character, viewGroup, false);
        return new CharacterViewHolder(view);
    }

    @Override public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        super.onBindViewHolder(viewHolder, i);
        CharacterViewHolder vh = (CharacterViewHolder) viewHolder; //safe cast
        Character character = characters.get(i);
        vh.tvName.setText(character.getName());
        vh.tvHeight.setText(character.getHeight());
        vh.tvMass.setText(character.getMass());
        vh.tvBirthday.setText(character.getBirthYear());
        vh.tvGender.setText(character.getGender());
    }

    @Override public int getItemCount() {
        return characters.size();
    }

    public void replaceData(List<Character> characters) {
        this.characters.clear();
        this.characters.addAll(characters);
        notifyDataSetChanged();
    }

    public Character getItem(int position) {
        if (position < 0 || position >= characters.size()) {
            throw new InvalidParameterException("Invalid item index");
        }
        return characters.get(position);
    }

    public void clearData() {
        characters.clear();
        notifyDataSetChanged();
    }
}
