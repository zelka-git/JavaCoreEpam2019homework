package ru.epam.javacore.homework20200205.cargo.repo;

import ru.epam.javacore.homework20200205.cargo.domain.Cargo;
import ru.epam.javacore.homework20200205.cargo.search.CargoSearchCondition;
import ru.epam.javacore.homework20200205.cargo.service.TypeSortCargo;
import ru.epam.javacore.homework20200205.common.business.repo.CommonRepo;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface CargoRepo extends CommonRepo<Cargo, Long>, Serializable {

    Optional<Cargo> getByIdFetchingTransportations(long id);

    List<Cargo> getByName(String name);

    List<Cargo> getAllSortedItems(TypeSortCargo typeSortCargo);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);

}
