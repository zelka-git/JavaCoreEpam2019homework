package ru.epam.javacore.homework20200207.transportation.repo.dbimpl;

import ru.epam.javacore.homework20200207.common.solutions.utils.DbUtils;
import ru.epam.javacore.homework20200207.common.solutions.utils.Mapper;
import ru.epam.javacore.homework20200207.transportation.domain.Transportation;
import ru.epam.javacore.homework20200207.transportation.repo.TransportationRepo;

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

        DbUtils.executeUpdate(sql, transportation, Mapper::setTransportation);
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "delete from transportations where id = ?;";
        return DbUtils.executeUpdate(sql, ps -> ps.setLong(1, id)) == 1;
    }

    @Override
    public List<Transportation> getAll() {
        String sql = "select * from transportations";
        return DbUtils.select(sql, ps ->{},
                Mapper::mapTransportation);
    }

    @Override
    public Optional<Transportation> getById(Long id) {
        String sql = "select * from transportations where id = ?";
        List<Transportation> transportationList = DbUtils.select(sql, ps -> ps.setLong(1, id),
                Mapper::mapTransportation);
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
