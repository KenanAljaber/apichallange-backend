package com.pokemonapichallangedemo.service;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pokemonapichallangedemo.dao.AbilityDao;

import com.pokemonapichallangedemo.dao.PokemonDAO;
import com.pokemonapichallangedemo.dao.TypeDAO;
import com.pokemonapichallangedemo.entity.Ability;
import com.pokemonapichallangedemo.entity.Pokemon;
import com.pokemonapichallangedemo.entity.Type;

@Transactional
@Service
public class ServiceImpl implements MyService {

    @Autowired
    private PokemonDAO pokemonRepo;
    @Autowired
    private AbilityDao abilityRepo;
    @Autowired
    private TypeDAO typeRepo;
  

    @Override
    public int savePokemon(Pokemon pokemon) {

        return pokemonRepo.save(pokemon);

    }

    @Override
    public Ability findAbilityByAbility(String ability) {
        Optional<Ability> opt = abilityRepo.findByAbility(ability);

        if (opt.isPresent()) {
            return opt.get();
        } 
            return null;
        
    }

    @Override
    public Type findTypeByType(String type) {
        Optional<Type> opt = typeRepo.findByType(type);
        if (opt.isPresent()) {
            return opt.get();
        } 
            return null;
        
    }

    @Override
    public int saveAbility(Ability ability) {
        return abilityRepo.save(ability);

    }

    @Override
    public int saveType(Type type) {
        return typeRepo.save(type);

    }

    @Override
    public List<Pokemon> getAllPokemons() {
       
        return pokemonRepo.getAllPokemons();
    }

    @Override
    public Pokemon findPokemonById(int id) {
        Optional<Pokemon>opt=pokemonRepo.findPokemonById(id);
        if(opt.isPresent()){
            return opt.get();
        }
        return null;
    }

    @Override
    public Long countPokemons() {
        
        return pokemonRepo.getPokemonCount();
    }

    }


