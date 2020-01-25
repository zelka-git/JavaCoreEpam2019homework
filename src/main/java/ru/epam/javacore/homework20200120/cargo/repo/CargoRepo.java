package ru.epam.javacore.homework20200120.cargo.repo;

import ru.epam.javacore.homework20200120.cargo.domain.Cargo;
import ru.epam.javacore.homework20200120.cargo.search.CargoSearchCondition;
import ru.epam.javacore.homework20200120.cargo.service.TypeSortCargo;
import ru.epam.javacore.homework20200120.common.business.repo.CommonRepo;

import java.io.Serializable;
import java.util.List;

public interface CargoRepo extends CommonRepo<Cargo, Long>, Serializable {

    Cargo getByIdFetchingTransportations(long id);

    List<Cargo> getByName(String name);

    List<Cargo> getAllSortedItems(TypeSortCargo typeSortCargo);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);

}
