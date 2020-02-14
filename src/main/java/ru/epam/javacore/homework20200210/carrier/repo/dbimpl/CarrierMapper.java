package ru.epam.javacore.homework20200210.carrier.repo.dbimpl;

import ru.epam.javacore.homework20200210.carrier.domain.Carrier;
import ru.epam.javacore.homework20200210.carrier.domain.CarrierType;
import ru.epam.javacore.homework20200210.storage.IdGenerator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CarrierMapper {

    public static Carrier mapCarrier(ResultSet rs, String prefix) throws Exception {
        Carrier carrier = new Carrier();
        carrier.setId(rs.getLong(prefix + "id"));
        carrier.setName(rs.getString(prefix + "name"));
        carrier.setAddress(rs.getString(prefix + "address"));
        carrier.setCarrierType(CarrierType.valueOf(rs.getString(prefix + "carrierType")));
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
}
