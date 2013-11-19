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
import com.syamka.hibernate.entity.City;
import com.syamka.hibernate.entity.Region;
import com.syamka.rest.helper.JqgridResponse;
import com.syamka.rest.jaxbean.JqgridResult;
import com.syamka.rest.jaxbean.OperationStatus;
import com.syamka.rest.jaxbean.item.CityItem;
import com.syamka.rest.jaxbean.item.RegionItem;

import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
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
public class CityService {

    /**
     * TODO если у нас только ОДИН город (то же с регионами), массив не упаковывается в [] и jqgrid ничего не понимает
     *
     *
     */

    @GET
    @Path(value="/list")
    public JqgridResult<CityItem> getCities(@Context UriInfo uriInfo,
                                            @PathParam("region_id") BigInteger region_id){

        Region r = Util.getEm().find(Region.class, region_id);

        JqgridResponse resp = new JqgridResponse(uriInfo);
        StringBuilder queryStr = new StringBuilder("SELECT c FROM cities c WHERE c.region=:region");
        if(!resp.getSidx().isEmpty()){
            queryStr.append(" ORDER BY ").append(resp.getSidx()).append(" ");
            if(!resp.getSord().isEmpty()){
                queryStr.append(resp.getSord());
            }
            else queryStr.append("ASC");
        }

        Query query = Util.getEm().createQuery(queryStr.toString())
                                  .setParameter("region", r)
                                  .setFirstResult(resp.getFirstResult())
                                  .setMaxResults(resp.getMaxResults());
        List<City> list = query.getResultList();

        List<CityItem> result = new LinkedList<CityItem>();
        for(City c: list)
            result.add(new CityItem(c));

        int cnt = ((Long) Util.getEm().createNamedQuery("Region.countCities").setParameter("region", r).getSingleResult()).intValue();
        int pages = (int) Math.ceil((cnt + 0.0)/(resp.getRows() + 0.0));

        return new JqgridResult<CityItem>(
                resp.getPage(),
                cnt,
                pages,
                result
        );
    }

    @POST
    @Path(value="/add")
    public OperationStatus addCity(@FormParam("title") String title,
                                   @PathParam("region_id") BigInteger region_id,
                                   @FormParam("locked") boolean locked){

        try{
            Region r = Util.getEm().find(Region.class, region_id);
            City city = new City();
            city.setTitle(title);
            city.setLocked(locked);
            city.setRegion(r);
            Util.getEm().persist(city);
            return new OperationStatus(true, "", city.getId().intValue());
        }
        catch (Exception e){
            return new OperationStatus(false, e.getMessage());
        }
    }

    @POST
    @Path(value="/edit/{id:[0-9]+}")
    public OperationStatus editCity(@PathParam("id") BigInteger id,
                                    @FormParam("title") String title,
                                    @FormParam("locked") boolean locked){
        try{
            City city = Util.getEm().find(City.class, id);
            city.setTitle(title);
            city.setLocked(locked);
            Util.getEm().merge(city);
            return new OperationStatus(true, "");
        }
        catch (Exception e){
            return new OperationStatus(false, e.getMessage());
        }
    }

    @POST
    @Path(value="/delete/{id:[0-9]+}")
    public OperationStatus deleteCity(@PathParam("id") BigInteger id){
        try{
            City city = Util.getEm().find(City.class, id);
            Util.getEm().remove(city);
            return new OperationStatus(true, "");
        }
        catch (Exception e){
            return new OperationStatus(false, e.getMessage());
        }
    }

}
