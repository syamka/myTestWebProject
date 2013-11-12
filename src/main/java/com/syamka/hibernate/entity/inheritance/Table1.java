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
 * Time: 0:02
 */
@Entity
@DiscriminatorValue("table1")
public class Table1 extends BaseTable {
    @Column(name="var1")
    protected String var1;

    public String getVar1() {
        return var1;
    }

    public void setVar1(String var1) {
        this.var1 = var1;
    }

    @Override
    public String toString() {
        return getTitle() + " " + getVar1() + " _ 1";
    }
}
