package com.pokemonapichallangedemo.dao;

import java.util.Optional;

import com.pokemonapichallangedemo.entity.Type;;

public interface TypeDAO {

    int save(Type type);

    Optional<Type> findByType(String type);

}
