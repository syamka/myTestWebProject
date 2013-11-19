package com.syamka.hibernate.entity;

import javax.persistence.*;
import java.math.BigInteger;

@Entity(name="regions")
@NamedQueries({
    @NamedQuery(name = "Region.count",
                query = "SELECT COUNT(r) FROM regions r"),
    @NamedQuery(name = "Region.countCities",
                query = "SELECT COUNT(c) FROM cities c WHERE c.region=:region")
})
public class Region {

    /**
     * 01.11.13
     * Важно. Стратегия GenerationType.IDENTITY позволяет использовать INSERT без генерации ID
     * (используется mysql AUTOINCREMENT)
     * При этом поле MUST BE private
     */

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private BigInteger id;

    @Column(name="title")
    protected String title;

    @Column(name="locked")
    protected boolean locked;

    public BigInteger getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @Override
    public String toString() {
        return getTitle() + " ["+ getId() +"]";
    }
}
