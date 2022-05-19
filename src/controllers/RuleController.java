package controllers;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import connection.Connection;

import entities.Rule;

public class RuleController {
    public List<Rule> getAll() {
        Query query = entityManager().createQuery("SELECT r FROM Rule r");
        return query.getResultList();
    }
    
    private EntityManager entityManager() {
        return Connection.getInstance().getEntityManagerFactory().createEntityManager();
    }
}
