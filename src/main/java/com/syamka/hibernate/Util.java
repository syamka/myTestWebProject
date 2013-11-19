package com.syamka.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Title: Утилы для общей работы приложения
 * Description: Для нормальной реализации  Open session In View
 * (https://community.jboss.org/wiki/OpenSessioninView?_sscc=t)
 * взято решение, см. http://stackoverflow.com/questions/15071238/entitymanager-threadlocal-pattern-with-jpa-in-jse
 * <p/>
 * User: valentina
 * Date: 08.11.13
 * Time: 22:28
 */
public class Util {

    public static final EntityManagerFactory emf;
    private static final ThreadLocal<EntityManager> threadLocal;

    static {
        try{
            emf = Persistence.createEntityManagerFactory("main");
            threadLocal = new ThreadLocal<EntityManager>();
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public static EntityManager getEm() {
        EntityManager em = threadLocal.get();

        if (em == null) {
            em = emf.createEntityManager();
            threadLocal.set(em);
        }
        return em;
    }

    public static void closeEm(){
        EntityManager em = threadLocal.get();
        if (em != null) {
            em.close();
            threadLocal.set(null);
        }
    }

}
