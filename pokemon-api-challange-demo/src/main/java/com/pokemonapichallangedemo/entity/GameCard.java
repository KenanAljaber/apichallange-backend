package com.pokemonapichallangedemo.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class GameCard implements Serializable {

    private Set<String> options;

    private Pokemon pokemon;

    public GameCard() {

    }

    public GameCard(Set<String> options, Pokemon pokemon) {
        this.options = options;
        this.pokemon = pokemon;
        options=new HashSet<>();
    }

    public boolean addOption(String option) {
        if (options == null) {
            options = new HashSet<>();
        }
        if (!options.contains(option) && options.size() < 3 &&
         !option.equals(pokemon.getName()) && !option.equals("")) {
            options.add(option);
            return true;
        }
        return false;
    }

    public Set<String> getOptions() {
        return this.options;
    }

    public void setOptions(Set<String> options) {
        this.options = options;
    }

    public Pokemon getPokemon() {
        return this.pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    @Override
    public String toString() {
        return "{" +
                " options='" + getOptions() + "'" +
                ", pokemon='" + getPokemon() + "'" +
                "}";
    }

}
