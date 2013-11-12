package com.syamka.hibernate.rest.jaxbean;

import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigInteger;

/**
 * Title:
 * Description:
 * <p/>
 * User: valentina
 * Date: 02.11.13
 * Time: 23:40
 */
@XmlRootElement
public class TariffScaleItem extends JqgridResultItem {

    public TariffScaleItem() {
    }

    public TariffScaleItem(BigInteger id, String title) {
        this.id = id;
        this.title = title;
    }

    public BigInteger id;
    public String title;

    public void setId(BigInteger id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
