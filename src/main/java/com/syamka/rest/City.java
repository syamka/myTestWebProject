/*
* (C) Copyright 1997 i-Teco, CJSK. All Rights reserved.
* i-Teco PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
* Эксклюзивные права 1997 i-Teco, ЗАО.
* Данные исходные коды не могут использоваться и быть изменены
* без официального разрешения компании i-Teco.          
*/
package com.syamka.rest;

import com.syamka.rest.jaxbean.JqgridResult;
import com.syamka.rest.jaxbean.OperationStatus;
import com.syamka.rest.jaxbean.item.CityItem;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

/**
 * <h3>REST сервис для городов</h3>
 * <p></p>
 * <p>Author: predtechenskaya (predtechenskaya@i-teco.ru)</p>
 * <p>Date: 14.11.13</p>
 */

//обратить внимание: PathParam определен в аннотации класса. но будет доступен в любой переменной метода
@Path(value = "/region/{region_id:[0-9]+}/city")
@Produces(MediaType.APPLICATION_JSON)
public class City {

    @GET
    @Path(value="/list")
    public JqgridResult<CityItem> getCities(@PathParam("region_id") int region_id){

        List<CityItem> result = new LinkedList<CityItem>();
        for(int i=0;i<10;i++){
            result.add(new CityItem(
                    BigInteger.valueOf(Math.round(Math.random() * 10000)),
                    "Город " + Math.round(Math.random() * 10000),
                    BigInteger.valueOf(Math.round(Math.random() * 10000)),
                    Math.random() > 0.5
            ));
        }
        return new JqgridResult<CityItem>(
                (int) Math.round(Math.random() * 100),
                (int) Math.round(Math.random() * 100),
                (int) Math.round(Math.random() * 100),
                result
        );
    }

    @POST
    @Path(value="/add")
    public OperationStatus addCity(@QueryParam("title") String title,
                                   @QueryParam("region_id") int region_id){
        return new OperationStatus(true, "");
    }

    @POST
    @Path(value="/edit/{id:[0-9]+}")
    public OperationStatus editCity(@PathParam("id") int id,
                                    @QueryParam("title") String title,
                                    @QueryParam("locked") boolean locked){
        return new OperationStatus(true, "");
    }

    @POST
    @Path(value="/delete/{id:[0-9]+}")
    public OperationStatus deleteCity(@PathParam("id") int id){
        return new OperationStatus(true, "");
    }

}
