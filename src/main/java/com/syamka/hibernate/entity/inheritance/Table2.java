package com.syamka.hibernate.entity.inheritance;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Title:
 * Description:
 * <p/>
 * User: valentina
 * Date: 12.11.13
 * Time: 0:03
 */
@Entity
@DiscriminatorValue("table2")
public class Table2 extends BaseTable {
    @Column(name="var2")
    protected String var2;

    public String getVar2() {
        return var2;
    }

    public void setVar2(String var2) {
        this.var2 = var2;
    }

    @Override
    public String toString() {
        return getTitle() + " " + getVar2() + " _ 2";
    }
}
