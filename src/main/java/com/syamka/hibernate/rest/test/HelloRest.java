package com.syamka.hibernate.rest.test;

import com.syamka.hibernate.entity.Region;
import com.syamka.hibernate.rest.jaxbean.test.JqgridResultTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

/**
 * Title: Попытка пощупать JAX-RS (используя Jersey)
 * Description:
 * <p/>
 * User: valentina
 * Date: 01.11.13
 * Time: 21:38
 *
 * TODO хотелось бы сделать полноценный REST-шлюз, т.е. select-update-insert-delete с сортировками-группировками
 *
 */
@Path("/region")
public class HelloRest {

    /**
     * Понять, как можно использовать @PersistenceContext и все такое, чтобы не инициализировать переменные вручную.
     * TODO то, что касается JPA, Transaction types и т.п.
     */
    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("main");

    public HelloRest(){

    }

    @GET
    public String sayHello(){
        return "Hello, Jersey World !";
    }


    /**
     * 02.11.13 Метод переписан для экспериментов с JSON (JAXB - based)
     * @return JSON
     */

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_XML)
    public JqgridResultTest<com.syamka.hibernate.rest.jaxbean.test.Region> sayHelloToItem(@QueryParam("test") int test){
        //это тупняк, я просто проверяю работоспособность REST !
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("SELECT r FROM regions r");
        List<com.syamka.hibernate.rest.jaxbean.test.Region> result = new LinkedList<com.syamka.hibernate.rest.jaxbean.test.Region>();
        for(Region r: (List<Region>)query.getResultList()){
            result.add(new com.syamka.hibernate.rest.jaxbean.test.Region(r));
        }
        em.close();

        return new JqgridResultTest<com.syamka.hibernate.rest.jaxbean.test.Region>(test,2,104,result);

    }

    @GET
    @Path("/jaxb")
    @Produces(MediaType.APPLICATION_XML)
    public com.syamka.hibernate.rest.jaxbean.test.Region getReg(){
        return new com.syamka.hibernate.rest.jaxbean.test.Region(BigInteger.valueOf(123),"Арбайтен");
    }


    @GET
    @Path("/ha/{title}")
    public String addRegion(@PathParam("title") String title){

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Region r = new Region();
        r.setTitle(title);
        em.persist(r);
        em.getTransaction().commit();
        em.close();
        return title;
    }

    /**
     * Это - локатор, который возвращает объект HelloRestItem с нужным id
     * Дальше с запросом разбирается уже дочерний объект
     */
    @Path("/item/{id}")
    public HelloRestItem getItem(@PathParam("id") String id){
        return new HelloRestItem(id);
    }

}
