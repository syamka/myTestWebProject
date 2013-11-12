package com.syamka.hibernate.rest;

import com.syamka.hibernate.rest.jaxbean.CityItem;
import com.syamka.hibernate.rest.jaxbean.JqgridResult;
import com.syamka.hibernate.rest.jaxbean.OperationStatus;
import com.syamka.hibernate.rest.jaxbean.RegionItem;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

/**
 * Title:
 * Description:
 * <p/>
 * User: valentina
 * Date: 02.11.13
 * Time: 23:42
 */
@Path(value = "regions")
public class Regions {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
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

    @GET
    @Path(value = "test")
    public RegionItem getReg(){
        return new RegionItem(BigInteger.valueOf(123),"asddffsf",false);
    }

    @POST
    @Path(value="/add")
    public OperationStatus addRegion(@QueryParam("title") String title){
        return new OperationStatus(true, "");
    }

    @POST
    @Path(value="/delete/{id:[0-9]+}")
    public OperationStatus deleteRegion(@QueryParam("id") int id){
        return new OperationStatus(true, "");
    }


    @GET
    @Path(value="/{id:[0-9]+}")
    public JqgridResult<CityItem> getCities(@PathParam("id") int region_id){

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
    @Path(value="/addCity")
    public OperationStatus addCity(@QueryParam("title") String title){
        return new OperationStatus(true, "");
    }

    @POST
    @Path(value="/deleteCity/{id:[0-9]+}")
    public OperationStatus deleteCity(@PathParam("id") int id){
        return new OperationStatus(true, "");
    }



}
