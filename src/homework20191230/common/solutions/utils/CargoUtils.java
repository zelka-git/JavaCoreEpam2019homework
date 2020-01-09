package homework20191230.common.solutions.utils;

import homework20191230.cargo.domain.Cargo;
import homework20191230.cargo.domain.ClothesCargo;
import homework20191230.cargo.domain.ComputerCargo;
import homework20191230.cargo.domain.FoodCargo;

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
