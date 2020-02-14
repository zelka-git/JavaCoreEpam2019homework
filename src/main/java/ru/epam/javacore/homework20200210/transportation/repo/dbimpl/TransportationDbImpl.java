package ru.epam.javacore.homework20200210.transportation.repo.dbimpl;

import ru.epam.javacore.homework20200210.cargo.domain.Cargo;
import ru.epam.javacore.homework20200210.cargo.repo.dbimpl.CargoMapper;
import ru.epam.javacore.homework20200210.carrier.domain.Carrier;
import ru.epam.javacore.homework20200210.carrier.repo.dbimpl.CarrierMapper;
import ru.epam.javacore.homework20200210.common.solutions.repo.jdbc.QueryWrapper;
import ru.epam.javacore.homework20200210.transportation.domain.Transportation;
import ru.epam.javacore.homework20200210.transportation.repo.TransportationRepo;

import java.util.List;
import java.util.Optional;

public class TransportationDbImpl implements TransportationRepo {

    @Override
    public void add(Transportation transportation) {
        String sql;
        sql = "insert into transportations " +
                "(id,  id_cargo, id_carrier, description, billTo, date) " +
                "values " +
                "(?, ?, ?, ?, ?, ?);";

        QueryWrapper.executeUpdate(sql, transportation, TransportationMapper::setTransportation);
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "delete from transportations where id = ?;";
        return QueryWrapper.executeUpdate(sql, ps -> ps.setLong(1, id)) == 1;
    }

    @Override
    public List<Transportation> getAll() {
//        String sql = "select * from transportations";
        String sql = "select tr.*, "
                +"crg.id as cargo_id,"
                +"crg.name as cargo_name,"
                +"crg.weight as cargo_weight,"
                +"crg.cargoType as cargo_cargoType,"
                +"crg.size as cargo_size,"
                +"crg.material as cargo_material,"
                +"crg.description as cargo_description,"
                +"crg.expiratonDate as cargo_expiratonDate,"
                +"crg.storeTemperature as cargo_storeTemperature,"
                +"cr.id as carrier_id,"
                +"cr.name as carrier_name,"
                +"cr.address as carrier_address,"
                +"cr.carrierType as carrier_carrierType"
                +" from transportations tr "
                +"inner join cargos crg on (tr.id_cargo = crg.id) "
                +"inner join carriers cr on (tr.id_carrier = cr.id)";
        return QueryWrapper.select(sql, ps ->{},
                rs->{
                Transportation tr = TransportationMapper.mapTransportation(rs);
                    Carrier carrier = CarrierMapper.mapCarrier(rs, "carrier_");
                    Cargo cargo = CargoMapper.mapCargo(rs, "cargo_");
                    tr.setCargo(cargo);
                    tr.setCarrier(carrier);
                    return tr;
                });
    }

    @Override
    public Optional<Transportation> getById(Long id) {
        String sql = "select * from transportations where id = ?";
        List<Transportation> transportationList = QueryWrapper.select(sql, ps -> ps.setLong(1, id),
                TransportationMapper::mapTransportation);
        if (transportationList.size() > 0) {
            return Optional.of(transportationList.get(0));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean update(Transportation element) {
        return false;
    }

    @Override
    public int countAll() {
        return getAll().size();
    }

}
