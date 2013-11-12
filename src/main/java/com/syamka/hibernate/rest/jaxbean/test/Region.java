package com.syamka.hibernate.rest.jaxbean.test;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigInteger;

/**
 * Title:
 * Description:
 * <p/>
 * User: valentina
 * Date: 02.11.13
 * Time: 18:58
 */
@XmlRootElement
public class Region extends JgridResultItem {

    public Region(){}

    public Region(com.syamka.hibernate.entity.Region region){
        id = region.getId();
        title = region.getTitle();
    }

    public Region(BigInteger id, String title){
        this.id=id;
        this.title = title;
    }

    public BigInteger id;
    public String title;

}
