package com.syamka.rest.jaxbean.item;

import com.syamka.hibernate.entity.City;

import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigInteger;

/**
 * Title:
 * Description:
 * <p/>
 * User: valentina
 * Date: 02.11.13
 * Time: 23:36
 */
@XmlRootElement
public class CityItem extends JqgridResultItem {

    public CityItem() {}

    public CityItem(BigInteger id, String title, boolean locked) {
        this.id = id;
        this.title = title;
        this.locked = locked;
    }

    public CityItem(City city){
        this(city.getId(), city.getTitle(), city.isLocked());
    }

    public BigInteger id;
    public String title;
    public boolean locked;

    public void setId(BigInteger id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
}
