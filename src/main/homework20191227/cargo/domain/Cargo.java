package main.homework20191227.cargo.domain;

import main.homework20191227.common.business.domain.BaseEntity;
import main.homework20191227.transportation.domain.Transportation;

import java.util.ArrayList;
import java.util.List;

public abstract class Cargo extends BaseEntity {
    protected String name;
    protected int weight;
    protected CargoType cargoType;
    protected List<Transportation> transportations = new ArrayList<>();

    public Cargo() {
        cargoType = this.getCargoType();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public abstract CargoType getCargoType();

    public void setCargoType(CargoType cargoType) {
        this.cargoType = cargoType;
    }

    public List<Transportation> getTransportations() {
        return transportations;
    }

    public void setTransportations(List<Transportation> transportations) {
        this.transportations = transportations;
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", cargoType=" + cargoType +
                ", transportations=" + transportations.toString() +
                '}';
    }
}
