package com.jeanpigomez.starwarsapp.data.api;

import com.google.gson.annotations.SerializedName;
import com.jeanpigomez.starwarsapp.data.model.Character;

import java.util.List;

public class PeopleResponse {

    @SerializedName("results")
    private List<Character> characters;

    private String next;


    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}
