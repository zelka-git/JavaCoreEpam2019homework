package ru.epam.javacore.homework20191230.common.solutions.utils;

import main.homework20191230.cargo.domain.Cargo;
import main.homework20191230.cargo.domain.ClothesCargo;
import main.homework20191230.cargo.domain.ComputerCargo;
import main.homework20191230.cargo.domain.FoodCargo;

public class CargoUtils {
    private CargoUtils(){
    }

    public static Cargo getCargoByCargoType(String cargoType){
        Cargo cargo;
        switch (cargoType) {
            case "FOOD":
                cargo = new FoodCargo();
                break;
            case "COMPUTER":
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
