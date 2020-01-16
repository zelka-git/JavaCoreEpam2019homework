package ru.epam.javacore.homework20191227.cargo.repo;

import ru.epam.javacore.homework20191227.cargo.domain.Cargo;
import ru.epam.javacore.homework20191227.cargo.service.TypeSortCargo;
import ru.epam.javacore.homework20191227.common.business.repo.CommonRepo;

import java.util.List;

public interface CargoRepo extends CommonRepo<Cargo, Long> {

    Cargo getByIdFetchingTransportations(long id);

    List<Cargo> getByName(String name);

    List<Cargo> getAllSortedItems(TypeSortCargo typeSortCargo);

}
