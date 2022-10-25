package com.pokemonapichallangedemo.service;

import java.util.List;

import com.pokemonapichallangedemo.entity.Ability;
import com.pokemonapichallangedemo.entity.Pokemon;
import com.pokemonapichallangedemo.entity.Type;

public interface MyService {
    
//Pokemon logic
int savePokemon (Pokemon pokemon);
List<Pokemon> getAllPokemons();
Pokemon findPokemonById(int id);
Long countPokemons();

//ability logic
Ability findAbilityByAbility(String ability);
int saveAbility(Ability ability);


//type logic 
Type findTypeByType(String type);
int saveType(Type type);

}
