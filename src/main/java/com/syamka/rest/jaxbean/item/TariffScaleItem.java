package com.syamka.rest.jaxbean.item;

import com.syamka.hibernate.entity.tariff.TariffScale;
import com.syamka.rest.jaxbean.item.JqgridResultItem;

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

    public TariffScaleItem(TariffScale entity){
        setId(entity.getId());
        setTitle(entity.getTitle());
        setDescription(entity.getDescription());
        setMain(entity.isMain());
    }

    public TariffScaleItem(BigInteger id, String title) {
        this.id = id;
        this.title = title;
    }

    public BigInteger id;
    public String title;
    public String description;
    public Boolean main;


    public void setId(BigInteger id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMain(boolean main) {
        this.main = main;
    }
}
