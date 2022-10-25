package com.pokemonapichallangedemo.dao;

import java.util.Optional;
import com.pokemonapichallangedemo.entity.Type;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TypeDAOImpl implements TypeDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Optional<Type> findByType(String type) {
        Session session = entityManager.unwrap(Session.class);
        Query<Type> query = session.createQuery("FROM Type t WHERE t.type= :type");
        query.setParameter("type", type);
        query.setMaxResults(1);
        try {
            Type typeObj = (Type) query.getSingleResult();
            return Optional.ofNullable(typeObj);
        } catch (NoResultException exception) {
            System.out.println("Entity does not exist");
            return Optional.ofNullable(null);

        }
    }

    @Override
    public int save(Type type) {
        Session session = entityManager.unwrap(Session.class);
        int typeId = (Integer) session.save(type);
        return typeId;

    }

}
