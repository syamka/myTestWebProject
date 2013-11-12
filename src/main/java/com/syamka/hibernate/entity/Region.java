package com.syamka.hibernate.entity;

import javax.persistence.*;
import java.math.BigInteger;

@Entity(name="regions")
public class Region {

    /**
     * 01.11.13
     * Важно. Стратегия GenerationType.IDENTITY позволяет использовать INSERT без генерации ID
     * (используется mysql AUTOINCREMENT)
     * При этом поле MUST BE private
     */

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name="title")
    protected String title;

    public BigInteger getId() {
        return id;
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
