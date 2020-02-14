package ru.epam.javacore.homework20200210.carrier.repo.dbimpl;

import ru.epam.javacore.homework20200210.carrier.domain.Carrier;
import ru.epam.javacore.homework20200210.carrier.repo.CarrierRepo;
import ru.epam.javacore.homework20200210.common.solutions.repo.jdbc.QueryWrapper;

import java.util.List;
import java.util.Optional;

public class CarrierDbImpl implements CarrierRepo {
    @Override
    public Optional<Carrier> getByIdFetchingTransportations(long id) {
        return getById(id);
    }

    @Override
    public List<Carrier> getByName(String name) {
        String sql = "select * from carriers where name = ?";
        return QueryWrapper.select(sql, ps -> ps.setString(1, name),
                rs -> CarrierMapper.mapCarrier(rs, ""));
    }


    @Override
    public Optional<Carrier> getById(Long id) {
        String sql = "select * from carriers where id = ?";
        List<Carrier> carriers = QueryWrapper.select(sql, ps -> ps.setLong(1, id),
                rs -> CarrierMapper.mapCarrier(rs, ""));
        if (carriers.size() > 0) {
            return Optional.of(carriers.get(0));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<Carrier> getAll() {
        String sql = "select * from carriers";
        return QueryWrapper.select(sql, ps -> {},
                rs -> CarrierMapper.mapCarrier(rs, ""));
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "delete from carriers where id = ?;";
        return QueryWrapper.executeUpdate(sql, ps -> ps.setLong(1, id)) == 1;
    }

    @Override
    public void add(Carrier element) {
        String sql;
        sql = "insert into carriers " +
                "(id,  name, address, carrierType) " +
                "values " +
                "(?, ?, ?, ?);";

        QueryWrapper.executeUpdate(sql, element, CarrierMapper::setCarrier);
    }

    @Override
    public void addList(List<Carrier> carriers) {
        String sql;
        sql = "insert into carriers " +
                "(id,  name, address, carrierType) " +
                "values " +
                "(?, ?, ?, ?);";

        QueryWrapper.executeUpdate(sql, carriers, CarrierMapper::setCarrier);
    }

    @Override
    public boolean update(Carrier element) {
        return false;
    }

    @Override
    public int countAll() {
        return getAll().size();
    }
}
