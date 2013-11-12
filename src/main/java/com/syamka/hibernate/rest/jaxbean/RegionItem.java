package com.syamka.hibernate.rest.jaxbean;

import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigInteger;

/**
 * Title:
 * Description:
 * <p/>
 * User: valentina
 * Date: 02.11.13
 * Time: 23:35
 */
@XmlRootElement
public class RegionItem extends JqgridResultItem {

    public RegionItem() {
    }

    public RegionItem(BigInteger id, String title, boolean locked) {
        this.id = id;
        this.title = title;
        this.locked = locked;
    }

    public BigInteger id;
    public String title;
    public Boolean locked;

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
