package homework20191211.cargo.domain;

import homework20191211.domain.Identification;
import homework20191211.transportation.Transportation;

import java.util.Arrays;

public class Cargo extends Identification {
    protected String name;
    protected int weight;
    protected CargoType cargoType;
    protected Transportation[] transportations;


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

    public CargoType getCargoType() {
        return cargoType;
    }

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
