package ru.epam.javacore.homework20200129.cargo.domain;

import ru.epam.javacore.homework20200129.cargo.domain.Cargo;
import ru.epam.javacore.homework20200129.cargo.domain.CargoType;

public class ComputerCargo extends Cargo {

    private String description;

    @Override
    public ru.epam.javacore.homework20200129.cargo.domain.CargoType getCargoType() {
        return CargoType.COMPUTERS;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", cargoType=" + cargoType +
                ", description='" + description + '\'' +
                ", transportations=" + transportations.toString() +
                '}';
    }
}
