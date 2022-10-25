package com.pokemonapichallangedemo.dao;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.pokemonapichallangedemo.entity.Ability;

@Repository
public class AbilityDAOImpl implements AbilityDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Optional<Ability> findByAbility(String ability) {
        Session session = entityManager.unwrap(Session.class);
        Query<Ability> query = session.createQuery("FROM Ability a WHERE a.ability= :ability");
        query.setParameter("ability", ability);
        query.setMaxResults(1);
        try {
            Ability abilityObj = (Ability) query.getSingleResult();
            return Optional.ofNullable(abilityObj);
        } catch (NoResultException exception) {
            System.out.println("Entity does not exist");
            return Optional.ofNullable(null);

        }

    }

    @Override
    public int save(Ability ability) {
        Session session = entityManager.unwrap(Session.class);
        int abilityId = (Integer) session.save(ability);
        return abilityId;

    }

}
