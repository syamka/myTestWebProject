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
import com.syamka.rest.jaxbean.item.TariffScaleItem;

import javax.ws.rs.*;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

/**
 * <h3>REST сервис для ТС</h3>
 * <p></p>
 * <p>Author: predtechenskaya (predtechenskaya@i-teco.ru)</p>
 * <p>Date: 14.11.13</p>
 */

@Path(value = "scale")
public class TariffScale {

    @GET
    @Path(value = "/list")
    public JqgridResult<TariffScaleItem> getScales(){
        List<TariffScaleItem> result = new LinkedList<TariffScaleItem>();
        for(int i=0;i<10;i++){
            result.add(new TariffScaleItem(
                BigInteger.valueOf(Math.round(Math.random() * 10000)),
                "Регион " + Math.round(Math.random() * 10000)
            ));
        }
        return new JqgridResult<TariffScaleItem>(
                (int) Math.round(Math.random() * 100),
                (int) Math.round(Math.random() * 100),
                (int) Math.round(Math.random() * 100),
                result
        );
    }

    @POST
    @Path(value="/add")
    public OperationStatus addScale(@QueryParam("title") String title){
        return new OperationStatus(true, "");
    }

    @POST
    @Path(value="/edit/{id:[0-9]+}")
    public OperationStatus editScale(@PathParam("id") int id,
                                     @QueryParam("title") String title){
        return new OperationStatus(true, "");
    }

    @POST
    @Path(value="/delete/{id:[0-9]+}")
    public OperationStatus deleteScale(@QueryParam("id") int id){
        return new OperationStatus(true, "");
    }

}
