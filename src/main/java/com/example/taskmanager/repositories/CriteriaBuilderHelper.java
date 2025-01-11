package com.example.taskmanager.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.stereotype.Component;

@Component
public class CriteriaBuilderHelper {
    private final EntityManager em;

    public CriteriaBuilderHelper(EntityManager em) {
        this.em = em;
    }

    public HibernateCriteriaBuilder getHibernateCriteriaBuilder() {
        var session = em.unwrap(Session.class);
        return session.getCriteriaBuilder();
    };
}
