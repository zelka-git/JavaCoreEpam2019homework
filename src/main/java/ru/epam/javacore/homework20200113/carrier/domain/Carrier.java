package ru.epam.javacore.homework20200113.carrier.domain;

import main.homework20200113.common.business.domain.BaseEntity;
import main.homework20200113.transportation.domain.Transportation;

import java.io.Serializable;
import java.util.List;

public class Carrier extends BaseEntity implements Serializable {
    private String name;
    private String address;
    private CarrierType carrierType;
    private List<Transportation> transportations;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CarrierType getCarrierType() {
        return carrierType;
    }

    public void setCarrierType(CarrierType carrierType) {
        this.carrierType = carrierType;
    }

    public List<Transportation> getTransportations() {
        return transportations;
    }

    public void setTransportations(List<Transportation> transportations) {
        this.transportations = transportations;
    }

    @Override
    public String toString() {
        return "Carrier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", carrierType=" + carrierType +
                ", transportations=" + transportations +
                '}';
    }
}
