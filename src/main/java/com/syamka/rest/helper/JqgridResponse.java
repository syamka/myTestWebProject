/*
* (C) Copyright 1997 i-Teco, CJSK. All Rights reserved.
* i-Teco PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
* Эксклюзивные права 1997 i-Teco, ЗАО.
* Данные исходные коды не могут использоваться и быть изменены
* без официального разрешения компании i-Teco.          
*/
package com.syamka.rest.helper;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

/**
 * <h3>Класс для управления (распознавания и хранения) параметров запроса jQGrid на выборку </h3>
 * <p>
 *     Для унифицированной обработки сортировок и т.п.
 * </p>
 * <p>Author: predtechenskaya (predtechenskaya@i-teco.ru)</p>
 * <p>Date: 19.11.13</p>
 */
public class JqgridResponse {

    public JqgridResponse(UriInfo uriInfo){
        MultivaluedMap<String, String> params =  uriInfo.getQueryParameters();
        page = Integer.parseInt(params.getFirst("page"));
        rows = Integer.parseInt(params.getFirst("rows"));
        sidx = params.getFirst("sidx");
        sord = params.getFirst("sord");
    }

    public int getFirstResult(){
        return getRows() * (getPage() - 1);
    }

    public int getMaxResults(){
        return getRows();
    }

    public int getPage() {
        return page;
    }

    public int getRows() {
        return rows;
    }

    public String getSidx() {
        return sidx;
    }

    public String getSord() {
        return sord;
    }

    // все пришедшие параметры Final
    private final String sord;
    private final String sidx;
    private final int rows;
    private final int page;
}
