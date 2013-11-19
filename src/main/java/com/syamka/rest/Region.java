package com.syamka.rest;

import com.syamka.rest.jaxbean.item.CityItem;
import com.syamka.rest.jaxbean.JqgridResult;
import com.syamka.rest.jaxbean.OperationStatus;
import com.syamka.rest.jaxbean.item.RegionItem;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

/**
 * Title: REST сервис для регионов
 * Description:
 * <p/>
 * User: valentina
 * Date: 02.11.13
 * Time: 23:42
 */
@Path(value = "region")
@Produces(MediaType.APPLICATION_JSON)
public class Region {
    @GET
    @Path(value = "list")
    public JqgridResult<RegionItem> getRegions(){
        List<RegionItem> result = new LinkedList<RegionItem>();
        for(int i=0;i<10;i++){
            result.add(new RegionItem(
                BigInteger.valueOf(Math.round(Math.random() * 10000)),
                "Регион " + Math.round(Math.random() * 10000),
                Math.random() > 0.5
            ));
        }
        return new JqgridResult<RegionItem>(
                (int) Math.round(Math.random() * 100),
                (int) Math.round(Math.random() * 100),
                (int) Math.round(Math.random() * 100),
                result
        );
    }

    @POST
    @Path(value="add")
    public OperationStatus addRegion(@QueryParam("title") String title){
        return new OperationStatus(true, "");
    }

    @POST
    @Path(value="edit/{id:[0-9]+}")
    public OperationStatus editRegion(@PathParam("id") int id,
                                      @QueryParam("title") String title,
                                      @QueryParam("locked") boolean locked){
        return new OperationStatus(false, "Ошибко !");
    }

    @POST
    @Path(value="delete/{id:[0-9]+}")
    public OperationStatus deleteRegion(@QueryParam("id") int id){
        return new OperationStatus(true, "");
    }

}
