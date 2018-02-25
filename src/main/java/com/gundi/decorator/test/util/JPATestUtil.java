package com.gundi.decorator.test.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by pai on 25.02.18.
 */
public class JPATestUtil {
    private static EntityManagerFactory factory;

    private static Map<String, EntityManager> entityManagers = new HashMap<String, EntityManager>();
    private static Properties dbProperties = null;



    public static EntityManagerFactory getEntityManagerFactory(String unitName) {

        if(null == factory) {
            factory = Persistence.createEntityManagerFactory(unitName);
        }
        return factory;
    }


    /**
     * Transaction helper Class to begin Transaction
     * @throws Exception
     */
    public static void beginTransaction() throws Exception {
        for (EntityManager entityManager : entityManagers.values()) {
            if (!entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().begin();
            }
        }
    }

    /**
     * Transaction helper Class to commit Transaction
     * @throws Exception
     */
    public static void commitTransaction() throws Exception {
        for (EntityManager entityManager : entityManagers.values()) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().commit();
            }
        }
    }

    /**
     * Transaction helper Class to rollback Transaction
     * @throws Exception
     */
    public static void rollbackTransaction() throws Exception {
        for (EntityManager entityManager : entityManagers.values()) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }
    }

    /**
     * Helper Class to clear EntityManager created by Framework
     */
    public static void clearEntityManagers() {
        for (EntityManager entityManager : entityManagers.values()) {
            if (null != entityManager) {
                entityManager.clear();
            }
        }
    }

    /**
     * Helper Class to clear EntityManager created by Framework
     */
    public static void clearEntityManager(String name) {
        EntityManager entityManager = entityManagers.get(name);
        if (null != entityManager) {
            entityManager.clear();
        }
    }

}