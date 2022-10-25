package com.pokemonapichallangedemo.entity;

import java.io.Serializable;

import java.util.HashSet;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"pokemons"})
@Entity
@Table(name = "ability")
public class Ability implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ability")
    private String ability;

    @ManyToMany(mappedBy = "abilities")
    private Set<Pokemon> pokemons=new HashSet<>();

    public Ability() {
    }

    public void addPokemon(Pokemon pokemon) {
        if (!pokemons.contains(pokemon)) {
           
      
        pokemons.add(pokemon);
        }
    }

    public Ability(String ability) {
        this.ability = ability;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAbility() {
        return this.ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public Set<Pokemon> getPokemons() {
        return this.pokemons;
    }

    public void setPokemons(Set<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }



    @Override
    public String toString() {
        return "{" +
            " id:'" + getId() + "'" +
            ", ability:'" + getAbility() + "'" +
            "}";
    }
   


}
