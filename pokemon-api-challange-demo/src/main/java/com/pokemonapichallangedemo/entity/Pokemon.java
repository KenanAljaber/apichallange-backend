package com.pokemonapichallangedemo.entity;

import java.io.Serializable;

import java.util.HashSet;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity 
@Table(name="pokemon")
public class Pokemon implements Serializable {

    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="weight")
    private int weight;
    
    @Column(name="height")
    private int height;

    @ManyToMany(cascade={
        CascadeType.ALL},
        fetch = FetchType.LAZY)
    @JoinTable(name="ability_pokemon",
    joinColumns = @JoinColumn(name="pokemon_id"),
    inverseJoinColumns = @JoinColumn(name="ability_id"))
    private Set<Ability> abilities=new HashSet<>();

    @ManyToMany(cascade={
        CascadeType.ALL},
        fetch = FetchType.LAZY)
    @JoinTable(name="type_pokemon",
    joinColumns = @JoinColumn(name="pokemon_id"),
    inverseJoinColumns = @JoinColumn(name="type_id"))
    private Set<Type> types=new HashSet<>();

    @Column(name="photo")
    private String photo;

    public Pokemon() {
      
    }

    public Pokemon(String name, int weight, int height) {
        this.name = name;
        this.weight = weight;
        this.height = height;
        
       
    }

    public void addAbility(Ability ability) {
        if( !this.abilities.contains(ability)){
            this.abilities.add(ability);
           // ability.addPokemon(this);
        }
       
    }

    public void addType(Type type) {
    if( !types.contains(type)){
        
        this.types.add(type);
        //type.addPokemon(this);
    }
        
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Set<Ability> getAbilities() {
        return this.abilities;
    }

    public void setAbilities(Set<Ability> abilities) {
        this.abilities = abilities;
    }

    public Set<Type> getTypes() {
        return this.types;
    }

   public void setTypes(Set<Type> types) {
        this.types = types;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public String getPhoto() {
        return this.photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }



    @Override
    public String toString() {
        return "{" +
           /*  " id:'" + getId() + "'" +
            ", name:'" + getName() + "'" +
            ", description:'" + getDescription() + "'" +
            ", weight:'" + getWeight() + "'" +
            ", height:'" + getHeight() + "'" +
            ", abilities:'" + getAbilities().toString() + "'" +
            ", types:'" + getTypes().toString() + "'" +
            ", photo:'" + getPhoto() + "'" +*/
            "}";
    }
   

}