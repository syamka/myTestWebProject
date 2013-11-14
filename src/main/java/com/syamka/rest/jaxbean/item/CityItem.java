package com.syamka.rest.jaxbean.item;

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

    public CityItem(BigInteger id, String title, BigInteger region_id, boolean locked) {
        this.id = id;
        this.title = title;
        this.region_id = region_id;
        this.locked = locked;
    }

    public BigInteger id;
    public String title;
    public BigInteger region_id;
    public boolean locked;

    public void setId(BigInteger id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRegion_id(BigInteger region_id) {
        this.region_id = region_id;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
}
