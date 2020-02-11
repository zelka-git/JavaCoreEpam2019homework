package ru.epam.javacore.homework20200210.common.solutions.utils;

import ru.epam.javacore.homework20200210.application.serviceholder.ServiceHolder;
import ru.epam.javacore.homework20200210.cargo.domain.Cargo;
import ru.epam.javacore.homework20200210.cargo.domain.ClothesCargo;
import ru.epam.javacore.homework20200210.cargo.domain.ComputerCargo;
import ru.epam.javacore.homework20200210.cargo.domain.FoodCargo;
import ru.epam.javacore.homework20200210.carrier.domain.Carrier;
import ru.epam.javacore.homework20200210.carrier.domain.CarrierType;
import ru.epam.javacore.homework20200210.storage.IdGenerator;
import ru.epam.javacore.homework20200210.transportation.domain.Transportation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import static ru.epam.javacore.homework20200210.common.solutions.utils.CargoUtils.getCargoByCargoType;

public class Mapper {
    private Mapper() {
    }

    public static Cargo mapCargo(ResultSet rs) throws Exception {
        String cargoType = rs.getString("cargoType");
        Cargo cargo = getCargoByCargoType(cargoType);
        cargo.setId(rs.getLong("id"));
        cargo.setName(rs.getString("name"));
        cargo.setWeight(rs.getInt("weight"));
        Class<? extends Cargo> aClass = cargo.getClass();
        if (aClass.equals(FoodCargo.class)) {
            ((FoodCargo) cargo).setExpirationDate(rs.getTimestamp("expiratonDate")
                    .toLocalDateTime().toLocalDate());
            ((FoodCargo) cargo).setStoreTemperature(rs.getInt("storeTemperature"));
        } else if (aClass.equals(ClothesCargo.class)) {
            ((ClothesCargo) cargo).setSize(rs.getString("size"));
            ((ClothesCargo) cargo).setMaterial(rs.getString("material"));
        } else if (aClass.equals(ComputerCargo.class)) {
            ((ComputerCargo) cargo).setDescription(rs.getString("description"));
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

    public static Carrier mapCarrier(ResultSet rs) throws Exception{
        Carrier carrier = new Carrier();
        carrier.setId(rs.getLong("id"));
        carrier.setName(rs.getString("name"));
        carrier.setAddress(rs.getString("address"));
        carrier.setCarrierType(CarrierType.valueOf(rs.getString("carrierType")));
        return carrier;
    }

    public static void setCarrier(PreparedStatement ps, Carrier carrier) throws Exception {
        int i = 0;
        carrier.setId(IdGenerator.generateId());
        ps.setLong(++i, carrier.getId());
        ps.setString(++i, carrier.getName());
        ps.setString(++i, carrier.getAddress());
        ps.setString(++i, carrier.getCarrierType().toString());
    }

    public static Transportation mapTransportation(ResultSet rs) throws Exception{
        Transportation transportation = new Transportation();
        transportation.setId(rs.getLong("id"));
        Long cargoId = rs.getLong("id_cargo");
        Long carrierId = rs.getLong("id_carrier");
        transportation.setDescription(rs.getString("description"));
        transportation.setBillTo(rs.getString("billTo"));
        transportation.setDate(rs.getTimestamp("date").toLocalDateTime().toLocalDate());
        Optional<Cargo> cargo = ServiceHolder.getInstance().getCargoService().getById(cargoId);
        cargo.ifPresent((el)-> transportation.setCargo(el));
        Optional<Carrier> carrier = ServiceHolder.getInstance().getCarrierService().getById(carrierId);
        carrier.ifPresent((el)-> transportation.setCarrier(el));
        return transportation;
    }

    public static void setTransportation(PreparedStatement ps, Transportation transportation)
            throws Exception{
        int i = 0;
        transportation.setId(IdGenerator.generateId());
        ps.setLong(++i, transportation.getId());
        ps.setLong(++i, transportation.getCargo().getId());
        ps.setLong(++i, transportation.getCarrier().getId());
        ps.setString(++i, transportation.getDescription());
        ps.setString(++i, transportation.getBillTo());
        ps.setTimestamp(++i, Timestamp.valueOf(LocalDateTime
                .of(transportation.getDate(), LocalTime.now())));
    }
}