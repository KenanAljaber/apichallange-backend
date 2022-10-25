package com.pokemonapichallangedemo.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pokemonapichallangedemo.entity.Pokemon;

@Repository
public class PokemonDAOImpl implements PokemonDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public int save(Pokemon pokemon) {
        Session session = entityManager.unwrap(Session.class);
        session.save(pokemon);
        System.out.println("INVOKED");
        return 0;

    }

    @Override
    public List<Pokemon> getAllPokemons() {
        Session session = entityManager.unwrap(Session.class);
        List<Pokemon> pokemons = session.createQuery("FROM Pokemon", Pokemon.class).list();
        /*
         * pokemons.forEach(it->{
         * // Hibernate.initialize(it.getAbilities());
         * // Hibernate.initialize(it.getTypes());
         * // it.getAbilities().size();
         * // it.getTypes().size();
         * // System.out.println(it);
         * });
         */
        return pokemons;
    }

    @Override
    public Optional<Pokemon> findPokemonById(int id) {
        Session session = entityManager.unwrap(Session.class);
        Pokemon pokeomn = session.get(Pokemon.class, id);
        Optional<Pokemon> opt = Optional.of(pokeomn);
        return opt;
    }

    @Override
    public Long getPokemonCount() {
        Session session = entityManager.unwrap(Session.class);
        Query<Long> query=session.createQuery("SELECT count(*) FROM Pokemon");
        Long t=query.uniqueResult();
        return t;
    }

}
