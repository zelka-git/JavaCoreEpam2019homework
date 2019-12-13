package homework20191211.cargo.domain;

import java.util.Arrays;

public class Computer extends Cargo {

    private CargoType cargoType = CargoType.COMPUTERS;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Computer{" +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", cargoType=" + cargoType +
                ", description='" + description + '\'' +
                ", transportations=" + Arrays.toString(transportations) +
                '}';
    }
}
