/*
* (C) Copyright 1997 i-Teco, CJSK. All Rights reserved.
* i-Teco PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
* Эксклюзивные права 1997 i-Teco, ЗАО.
* Данные исходные коды не могут использоваться и быть изменены
* без официального разрешения компании i-Teco.          
*/
package com.syamka.rest;

import com.syamka.hibernate.Util;
import com.syamka.hibernate.entity.tariff.TariffScale;
import com.syamka.rest.helper.JqgridResponse;
import com.syamka.rest.jaxbean.JqgridResult;
import com.syamka.rest.jaxbean.OperationStatus;
import com.syamka.rest.jaxbean.item.TariffScaleItem;

import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
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
@Produces(MediaType.APPLICATION_JSON)
public class TariffScaleService {

    @GET
    @Path(value = "/list")
    public JqgridResult<TariffScaleItem> getScales(@Context UriInfo uriInfo){
        JqgridResponse resp = new JqgridResponse(uriInfo);

        StringBuilder queryStr = new StringBuilder("SELECT ts FROM tariff_scale ts");
        if(!resp.getSidx().isEmpty()){
            queryStr.append(" ORDER BY ").append(resp.getSidx()).append(" ");
            if(!resp.getSord().isEmpty()){
                queryStr.append(resp.getSord());
            }
            else queryStr.append("ASC");
        }

        Query query = Util.getEm().createQuery(queryStr.toString())
                                  .setFirstResult(resp.getFirstResult())
                                  .setMaxResults(resp.getMaxResults());
        List<TariffScale> list = query.getResultList();
        List<TariffScaleItem> result = new LinkedList<TariffScaleItem>();
        for(TariffScale ts: list)
            result.add(new TariffScaleItem(ts));

        int cnt = ((Long) Util.getEm().createNamedQuery("TariffScale.count").getSingleResult()).intValue();
        int pages = (int) Math.ceil((cnt + 0.0)/resp.getRows());

        return new JqgridResult<TariffScaleItem>(
                resp.getPage(),
                cnt,
                pages,
                result
        );
    }

    @POST
    @Path(value="/add")
    public OperationStatus addScale(@FormParam("title") String title,
                                    @FormParam("description") String description){
        try{
            TariffScale tariffScale = new TariffScale();
            tariffScale.setTitle(title);
            tariffScale.setMain(false);
            tariffScale.setDescription(description);
            Util.getEm().persist(tariffScale);
            return new OperationStatus(true, "", tariffScale.getId().intValue());
        }
        catch (Exception e){
            return new OperationStatus(false, e.getMessage());
        }
    }

    @POST
    @Path(value="/edit/{id:[0-9]+}")
    public OperationStatus editScale(@PathParam("id") BigInteger id,
                                     @FormParam("title") String title,
                                     @FormParam("description") String description,
                                     @FormParam("main") boolean main){
        try{
            TariffScale tariffScale = Util.getEm().find(TariffScale.class, id);
            tariffScale.setTitle(title);
            tariffScale.setDescription(description);
            if(main){
                TariffScale oldMain = getMainTariffScale();
                oldMain.setMain(false);
                Util.getEm().merge(oldMain);
            }
            tariffScale.setMain(main);
            Util.getEm().merge(tariffScale);

            return new OperationStatus(true, "");

        }
        catch(Exception e){
            Util.getEm().getTransaction().rollback();
            return new OperationStatus(false, e.getMessage());
        }
    }

    @POST
    @Path(value="/delete/{id:[0-9]+}")
    public OperationStatus deleteScale(@PathParam("id") BigInteger id){
        try{
            TariffScale tariffScale = Util.getEm().find(TariffScale.class, id);
            Util.getEm().remove(tariffScale);
            return new OperationStatus(true, "");
        }
        catch(Exception e){
            return new OperationStatus(false, e.getMessage());
        }
    }

    protected TariffScale getMainTariffScale(){
        Query query = Util.getEm().createNamedQuery("TariffScale.main");
        return (TariffScale) query.getSingleResult();
    }


}
