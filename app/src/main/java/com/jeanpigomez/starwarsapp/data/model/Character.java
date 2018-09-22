package com.jeanpigomez.starwarsapp.data.model;

import com.google.gson.annotations.SerializedName;
import com.jeanpigomez.starwarsapp.data.Config;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(tableName = Config.CHARACTER_TABLE_NAME)
public class Character implements Serializable {

    @SerializedName("name")
    @PrimaryKey
    private @NonNull String name;
    @SerializedName("height")
    private String height;
    @SerializedName("mass")
    private String mass;
    @SerializedName("birth_year")
    private String birthYear;
    @SerializedName("gender")
    private String gender;
    @SerializedName("homeworld")
    private String homeworldURI;
    @SerializedName("homeworldObject")
    @Embedded(prefix = "homeworld_")
    private Homeworld homeworld;
    @SerializedName("films")
    private ArrayList<String> filmsURI;
//    @SerializedName("filmsObject")
//    private ArrayList<Film> films;
    @SerializedName("vehicles")
    private ArrayList<String> vehiclesURI;
//    @SerializedName("vehiclesObject")
//    private ArrayList<Vehicle> vehicles;

    public Character(String name, String height, String mass, String birthYear, String gender, String homeworldURI, ArrayList<String> filmsURI, ArrayList<String> vehiclesURI) {
        this.name = name;
        this.height = height;
        this.mass = mass;
        this.birthYear = birthYear;
        this.gender = gender;
        this.homeworldURI = homeworldURI;
        this.filmsURI = filmsURI;
        this.vehiclesURI = vehiclesURI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHomeworldURI() {
        return homeworldURI;
    }

    public void setHomeworldURI(String homeworldURI) {
        this.homeworldURI = homeworldURI;
    }

    public Homeworld getHomeworld() {
        return homeworld;
    }

    public void setHomeworld(Homeworld homeworld) {
        this.homeworld = homeworld;
    }

//    public ArrayList<Film> getFilms() {
//        return films;
//    }
//
//    public void setFilms(ArrayList<Film> films) {
//        this.films = films;
//    }
//
//    public ArrayList<Vehicle> getVehicles() {
//        return vehicles;
//    }
//
//    public void setVehicles(ArrayList<Vehicle> vehicles) {
//        this.vehicles = vehicles;
//    }

    public ArrayList<String> getFilmsURI() {
        return filmsURI;
    }

    public void setFilmsURI(ArrayList<String> filmsURI) {
        this.filmsURI = filmsURI;
    }

    public ArrayList<String> getVehiclesURI() {
        return vehiclesURI;
    }

    public void setVehiclesURI(ArrayList<String> vehiclesURI) {
        this.vehiclesURI = vehiclesURI;
    }
}
