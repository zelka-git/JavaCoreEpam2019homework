package ru.epam.javacore.homework20200210.transportation.repo.dbimpl;

import ru.epam.javacore.homework20200210.application.serviceholder.ServiceHolder;
import ru.epam.javacore.homework20200210.cargo.domain.Cargo;
import ru.epam.javacore.homework20200210.cargo.domain.ProxyCargo;
import ru.epam.javacore.homework20200210.carrier.domain.Carrier;
import ru.epam.javacore.homework20200210.carrier.domain.ProxyCarrier;
import ru.epam.javacore.homework20200210.storage.IdGenerator;
import ru.epam.javacore.homework20200210.transportation.domain.Transportation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

public class TransportationMapper {

    public static Transportation mapTransportation(ResultSet rs) throws Exception {
        Transportation transportation = new Transportation();
        transportation.setId(rs.getLong("id"));
        Long cargoId = rs.getLong("id_cargo");
        Long carrierId = rs.getLong("id_carrier");
        transportation.setDescription(rs.getString("description"));
        transportation.setBillTo(rs.getString("billTo"));
        transportation.setDate(rs.getTimestamp("date").toLocalDateTime().toLocalDate());
        transportation.setCargo(new ProxyCargo(cargoId));
        transportation.setCarrier(new ProxyCarrier(carrierId));
        return transportation;
    }

    public static void setTransportation(PreparedStatement ps, Transportation transportation)
            throws Exception {
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
