package ru.epam.javacore.homework20200207.carrier.repo.dbimpl;

import ru.epam.javacore.homework20200207.carrier.domain.Carrier;
import ru.epam.javacore.homework20200207.carrier.repo.CarrierRepo;
import ru.epam.javacore.homework20200207.common.solutions.utils.DbUtils;
import ru.epam.javacore.homework20200207.common.solutions.utils.Mapper;

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
        return DbUtils.select(sql, ps -> ps.setString(1, name),
                Mapper::mapCarrier);
    }

    @Override
    public Optional<Carrier> getById(Long id) {
        String sql = "select * from carriers where id = ?";
        List<Carrier> carriers = DbUtils.select(sql, ps -> ps.setLong(1, id),
                Mapper::mapCarrier);
        if (carriers.size() > 0) {
            return Optional.of(carriers.get(0));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<Carrier> getAll() {
        String sql = "select * from carriers";
        return DbUtils.select(sql, ps ->{},
                Mapper::mapCarrier);
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "delete from carriers where id = ?;";
        return DbUtils.executeUpdate(sql, ps -> ps.setLong(1, id)) == 1;
    }

    @Override
    public void add(Carrier element) {
        String sql;
        sql = "insert into carriers " +
                "(id,  name, address, carrierType) " +
                "values " +
                "(?, ?, ?, ?);";

        DbUtils.executeUpdate(sql, element, Mapper::setCarrier);
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
