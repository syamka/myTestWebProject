package com.syamka.hibernate.rest.test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Title: Дочерний ресурс JAX-RS
 * Description: Инициализируется локатором по id, после этого обрабатывает запросы как хочет.
 * <p/>
 * User: valentina
 * Date: 01.11.13
 * Time: 23:54
 */
public class HelloRestItem {

    private String id;
    public HelloRestItem(String id){
        this.id = id;
    }

    @GET
    public String getName(){
        return "Айтем " + id;
    }
}
