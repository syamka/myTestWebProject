/*
* (C) Copyright 1997 i-Teco, CJSK. All Rights reserved.
* i-Teco PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
* Эксклюзивные права 1997 i-Teco, ЗАО.
* Данные исходные коды не могут использоваться и быть изменены
* без официального разрешения компании i-Teco.          
*/
package com.syamka.hibernate.entity;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * <h3></h3>
 * <p></p>
 * <p>Author: predtechenskaya (predtechenskaya@i-teco.ru)</p>
 * <p>Date: 19.11.13</p>
 */
@Entity(name="cities")
public class City {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private BigInteger id;

    @Column(name="title")
    protected String title;

    @Column(name="locked")
    protected boolean locked;

    //регион нам не обязательно нужен. вообще, пока используем LAZY-загрузку везде, где только можно.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="region_id")
    protected Region region;

    public BigInteger getId() {
        return id;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return getTitle() + " ["+ getId() +"]";
    }
}
