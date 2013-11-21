/*
* (C) Copyright 1997 i-Teco, CJSK. All Rights reserved.
* i-Teco PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
* Эксклюзивные права 1997 i-Teco, ЗАО.
* Данные исходные коды не могут использоваться и быть изменены
* без официального разрешения компании i-Teco.          
*/
package com.syamka.hibernate.entity.tariff;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

/**
 * <h3></h3>
 * <p></p>
 * <p>Author: predtechenskaya (predtechenskaya@i-teco.ru)</p>
 * <p>Date: 21.11.13</p>
 */
@Entity(name="tariff_scale")
public class TariffScale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    public BigInteger getId() {
        return id;
    }

    protected String title;
    protected String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @OneToMany(mappedBy = "tariffScale", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    protected List<CourierTariff> courierTariffs;
    @OneToMany(mappedBy = "tariffScale", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    protected List<PickupTariff> pickupTariffs;
    @OneToMany(mappedBy = "tariffScale", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    protected List<RkoTariff> rkoTariffs;
    @OneToMany(mappedBy = "tariffScale", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    protected List<ReturnTariff> returnTariffs;
    @OneToMany(mappedBy = "tariffScale", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    protected List<AdditionalCourierTariff> additionalCourierTariffs;
    @OneToMany(mappedBy = "tariffScale", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    protected List<InsuranceTariff> insuranceTariffs;

    public void setAdditionalCourierTariffs(List<AdditionalCourierTariff> additionalCourierTariffs) {
        for(AdditionalCourierTariff t: additionalCourierTariffs)
            t.setTariffScale(this);
        this.additionalCourierTariffs = additionalCourierTariffs;
    }

    public void setCourierTariffs(List<CourierTariff> courierTariffs) {
        for(CourierTariff t: courierTariffs)
            t.setTariffScale(this);
        this.courierTariffs = courierTariffs;
    }

    public void setInsuranceTariffs(List<InsuranceTariff> insuranceTariffs) {
        for(InsuranceTariff t: insuranceTariffs)
            t.setTariffScale(this);

        this.insuranceTariffs = insuranceTariffs;
    }

    public void setPickupTariffs(List<PickupTariff> pickupTariffs) {
        for(PickupTariff t: pickupTariffs)
            t.setTariffScale(this);

        this.pickupTariffs = pickupTariffs;
    }

    public void setReturnTariffs(List<ReturnTariff> returnTariffs) {
        for(ReturnTariff t: returnTariffs)
            t.setTariffScale(this);

        this.returnTariffs = returnTariffs;
    }

    public void setRkoTariffs(List<RkoTariff> rkoTariffs) {
        for(RkoTariff t: rkoTariffs)
            t.setTariffScale(this);

        this.rkoTariffs = rkoTariffs;
    }

    public TariffScale(){}

    public TariffScale(TariffScale model){
        setCourierTariffs(model.courierTariffs);
        setPickupTariffs(model.pickupTariffs);
        setRkoTariffs(model.rkoTariffs);
        setReturnTariffs(this.returnTariffs = model.returnTariffs);
        setAdditionalCourierTariffs(model.additionalCourierTariffs);
        setInsuranceTariffs(model.insuranceTariffs);
    }


}
