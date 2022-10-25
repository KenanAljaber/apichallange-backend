package com.pokemonapichallangedemo.dao;

import java.util.List;
import java.util.Optional;

import com.pokemonapichallangedemo.entity.Pokemon;

public interface PokemonDAO {

    int save(Pokemon pokemon);

    List<Pokemon> getAllPokemons();

    Optional<Pokemon> findPokemonById(int id);

    Long getPokemonCount ();
}
