package main.homework20191230.cargo.domain;

public class ComputerCargo extends Cargo {

    private String description;

    @Override
    public CargoType getCargoType() {
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
