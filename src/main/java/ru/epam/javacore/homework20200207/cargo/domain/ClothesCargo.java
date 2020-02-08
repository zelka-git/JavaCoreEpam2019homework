package ru.epam.javacore.homework20200207.cargo.domain;

import ru.epam.javacore.homework20200207.cargo.domain.Cargo;
import ru.epam.javacore.homework20200207.cargo.domain.CargoType;

public class ClothesCargo extends Cargo {
    private String size;
    private String material;

    @Override
    public CargoType getCargoType() {
        return CargoType.CLOTHES;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}
