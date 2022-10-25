package com.pokemonapichallangedemo.dao;

import java.util.Optional;

import com.pokemonapichallangedemo.entity.Ability;

public interface AbilityDao {

    int save(Ability ability);

    Optional<Ability> findByAbility(String ability);

}
