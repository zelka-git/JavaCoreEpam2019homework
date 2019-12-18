package homework20191216.cargo.domain;

import homework20191216.common.domain.BaseEntity;
import homework20191216.transportation.domain.Transportation;

import java.util.Arrays;

public abstract class Cargo extends BaseEntity {
    protected String name;
    protected int weight;
    protected CargoType cargoType;
    protected Transportation[] transportations;

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

    public Transportation[] getTransportations() {
        return transportations;
    }

    public void setTransportations(Transportation[] transportations) {
        this.transportations = transportations;
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", cargoType=" + cargoType +
                ", transportations=" + Arrays.toString(transportations) +
                '}';
    }
}
