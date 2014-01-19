package com.syamka.rest.jaxbean.item.tariff;

/**
 * Title:
 * Description:
 * <p/>
 * User: valentina
 * Date: 19.01.14
 * Time: 21:29
 */
public class FixCalculationItem {

    public FixCalculationItem(double price) {
        this.price = price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double price;

}
