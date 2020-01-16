package ru.epam.javacore.homework20191211.cargo.domain;

import java.util.Arrays;

public class Food extends Cargo {
    private CargoType cargoType = CargoType.FOOD;
    private String Produced;
    private String ValidUntil;

    public String getProduced() {
        return Produced;
    }

    public void setProduced(String produced) {
        Produced = produced;
    }

    public String getValidUntil() {
        return ValidUntil;
    }

    public void setValidUntil(String validUntil) {
        ValidUntil = validUntil;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", cargoType=" + cargoType +
                ", Produced='" + Produced + '\'' +
                ", ValidUntil='" + ValidUntil + '\'' +
                ", transportations=" + Arrays.toString(transportations) +
                '}';
    }
}
