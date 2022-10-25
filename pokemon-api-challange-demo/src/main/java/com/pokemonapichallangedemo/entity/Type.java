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
@Table(name="type")
public class Type implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="type")
    private String type;

    @ManyToMany(mappedBy = "types")
    private Set<Pokemon> pokemons=new HashSet<>();

    public Type(){

    }

    public Type(String type){
        this.type=type;

    }

    public void addPokemon(Pokemon pokemon){
        if(!pokemons.contains(pokemon)){
            this.pokemons.add(pokemon);    
        }
        
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
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
            ", type:'" + getType() + "'" +
            "}";
    }


}
