package com.syamka.rest;

import com.syamka.hibernate.Util;
import com.syamka.hibernate.entity.Region;
import com.syamka.rest.helper.JqgridResponse;
import com.syamka.rest.jaxbean.JqgridResult;
import com.syamka.rest.jaxbean.OperationStatus;
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
 * Title: REST сервис для регионов
 * Description:
 * <p/>
 * User: valentina
 * Date: 02.11.13
 * Time: 23:42
 */
@Path(value = "region")
@Produces(MediaType.APPLICATION_JSON)
public class RegionService {
    @GET
    @Path(value = "list")
    public JqgridResult<RegionItem> getRegions(@Context UriInfo uriInfo){
        JqgridResponse resp = new JqgridResponse(uriInfo);

        //TODO подумать об унифицированных запросах
        StringBuilder queryStr = new StringBuilder("SELECT r FROM regions r");
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
        List<Region> list = query.getResultList();

        List<RegionItem> result = new LinkedList<RegionItem>();
        for(Region r: list)
            result.add(new RegionItem(r));

        int cnt = ((Long) Util.getEm().createNamedQuery("Region.count").getSingleResult()).intValue();
        int pages = (int) Math.ceil(cnt/resp.getRows());

        return new JqgridResult<RegionItem>(
                resp.getPage(),
                cnt,
                pages,
                result
        );
    }

    @POST
    @Path(value="add")
    public OperationStatus addRegion(@FormParam("title") String title,
                                     @FormParam("locked") boolean locked){

        try{
            Region region = new Region();
            region.setTitle(title);
            region.setLocked(locked);
            Util.getEm().persist(region);
            return new OperationStatus(true, "", region.getId().intValue());
        }
        catch(Exception e){
            return new OperationStatus(false, e.getMessage());
        }
    }

    @POST
    @Path(value="edit/{id:[0-9]+}")
    public OperationStatus editRegion(@PathParam("id") BigInteger id,
                                      @FormParam("title") String title,
                                      @FormParam("locked") boolean locked){

        try{
            Region region = Util.getEm().find(Region.class, id);
            region.setTitle(title);
            region.setLocked(locked);
            Util.getEm().merge(region);
            return new OperationStatus(true, "");
        }
        catch(Exception e){
            return new OperationStatus(false, e.getMessage());
        }
    }

    @POST
    @Path(value="delete/{id:[0-9]+}")
    public OperationStatus deleteRegion(@PathParam("id") BigInteger id){
        try{
            Region region = Util.getEm().find(Region.class, id);
            int cnt = ((Long)Util.getEm().createNamedQuery("Region.countCities").setParameter("region", region).getSingleResult()).intValue();
            if(cnt > 0)
                throw new Exception("Невозможно удалить регион, у которого заданы города");
            Util.getEm().remove(region);
            return new OperationStatus(true, "");
        }
        catch(Exception e){
            return new OperationStatus(false, e.getMessage());
        }
    }

}
