package ru.epam.javacore.homework20200207.common.solutions.utils;

import ru.epam.javacore.homework20200207.cargo.domain.Cargo;
import ru.epam.javacore.homework20200207.cargo.domain.ClothesCargo;
import ru.epam.javacore.homework20200207.cargo.domain.ComputerCargo;
import ru.epam.javacore.homework20200207.cargo.domain.FoodCargo;

public class CargoUtils {
    private CargoUtils(){
    }

    public static Cargo getCargoByCargoType(String cargoType){
        Cargo cargo;
        switch (cargoType) {
            case "FOOD":
                cargo = new FoodCargo();
                break;
            case "COMPUTERS":
                cargo = new ComputerCargo();
                break;
            case "CLOTHES":
                cargo = new ClothesCargo();
                break;
            default:
                cargo = null;
        }
        return cargo;
    }
}
