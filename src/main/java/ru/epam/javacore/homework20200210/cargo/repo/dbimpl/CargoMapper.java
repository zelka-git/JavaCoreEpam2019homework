package ru.epam.javacore.homework20200210.cargo.repo.dbimpl;

import ru.epam.javacore.homework20200210.cargo.domain.Cargo;
import ru.epam.javacore.homework20200210.cargo.domain.ClothesCargo;
import ru.epam.javacore.homework20200210.cargo.domain.ComputerCargo;
import ru.epam.javacore.homework20200210.cargo.domain.FoodCargo;
import ru.epam.javacore.homework20200210.storage.IdGenerator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static ru.epam.javacore.homework20200210.common.solutions.utils.CargoUtils.getCargoByCargoType;

public class CargoMapper {

    public static Cargo mapCargo(ResultSet rs, String prefix) throws Exception {
        String cargoType = rs.getString(prefix + "cargoType");
        Cargo cargo = getCargoByCargoType(cargoType);
        cargo.setId(rs.getLong(prefix + "id"));
        cargo.setName(rs.getString(prefix + "name"));
        cargo.setWeight(rs.getInt(prefix + "weight"));
        Class<? extends Cargo> aClass = cargo.getClass();
        if (aClass.equals(FoodCargo.class)) {
            ((FoodCargo) cargo).setExpirationDate(rs.getTimestamp(prefix + "expiratonDate")
                    .toLocalDateTime().toLocalDate());
            ((FoodCargo) cargo).setStoreTemperature(rs.getInt(prefix + "storeTemperature"));
        } else if (aClass.equals(ClothesCargo.class)) {
            ((ClothesCargo) cargo).setSize(rs.getString(prefix + "size"));
            ((ClothesCargo) cargo).setMaterial(rs.getString(prefix + "material"));
        } else if (aClass.equals(ComputerCargo.class)) {
            ((ComputerCargo) cargo).setDescription(rs.getString(prefix + "description"));
        }
        return cargo;
    }

    public static void setCargo(PreparedStatement ps, Cargo cargo) throws Exception {
        int i = 0;
        cargo.setId(IdGenerator.generateId());
        ps.setLong(++i, cargo.getId());
        ps.setString(++i, cargo.getName());
        ps.setInt(++i, cargo.getWeight());
        ps.setString(++i, cargo.getCargoType().toString());
        Class<? extends Cargo> aClass = cargo.getClass();
        if (aClass.equals(FoodCargo.class)) {
            ps.setString(++i, null);
            ps.setString(++i, null);
            ps.setString(++i, null);
            ps.setTimestamp(++i, Timestamp.valueOf(LocalDateTime
                    .of(((FoodCargo) cargo).getExpirationDate(), LocalTime.now())));
            ps.setInt(++i, ((FoodCargo) cargo).getStoreTemperature());
        } else if (aClass.equals(ClothesCargo.class)) {
            ps.setString(++i, ((ClothesCargo) cargo).getSize());
            ps.setString(++i, ((ClothesCargo) cargo).getMaterial());
            ps.setString(++i, null);
            ps.setTimestamp(++i, null);
            ps.setInt(++i, 0);
        } else if (aClass.equals(ComputerCargo.class)) {
            ps.setString(++i, null);
            ps.setString(++i, null);
            ps.setString(++i, ((ComputerCargo) cargo).getDescription());
            ps.setTimestamp(++i, null);
            ps.setInt(++i, 0);
        }
    }
}
