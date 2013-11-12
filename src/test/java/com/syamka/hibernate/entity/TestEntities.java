package com.syamka.hibernate.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Title: базовый класс для тестирование Entity
 * Description: для инкапсуляции технических деталей вроде получения EM
 * <p/>
 * User: valentina
 * Date: 01.11.13
 * Time: 19:50
 */
public class TestEntities {
    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("main");
    protected EntityManager em = null;
    public EntityManager getEntityManager(){
        if(em == null)
            em = emf.createEntityManager();
        return em;
    }
}
