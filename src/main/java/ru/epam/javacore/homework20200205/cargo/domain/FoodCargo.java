package ru.epam.javacore.homework20200205.cargo.domain;

import ru.epam.javacore.homework20200205.cargo.domain.Cargo;
import ru.epam.javacore.homework20200205.cargo.domain.CargoType;

import java.time.LocalDate;

public class FoodCargo extends Cargo {

    private LocalDate expirationDate;
    private int storeTemperature;

    @Override
    public ru.epam.javacore.homework20200205.cargo.domain.CargoType getCargoType() {
        return CargoType.FOOD;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getStoreTemperature() {
        return storeTemperature;
    }

    public void setStoreTemperature(int storeTemperature) {
        this.storeTemperature = storeTemperature;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", cargoType=" + cargoType +
                ", expiration date='" + expirationDate + '\'' +
                ", store temperature='" + storeTemperature + '\'' +
                ", transportations=" + transportations.toString() +
                '}';
    }
}
