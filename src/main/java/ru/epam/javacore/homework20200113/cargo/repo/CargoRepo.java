package ru.epam.javacore.homework20200113.cargo.repo;

import main.homework20200113.cargo.domain.Cargo;
import main.homework20200113.cargo.service.TypeSortCargo;
import main.homework20200113.common.business.repo.CommonRepo;

import java.io.Serializable;
import java.util.List;

public interface CargoRepo extends CommonRepo<Cargo, Long>, Serializable {

    Cargo getByIdFetchingTransportations(long id);

    List<Cargo> getByName(String name);

    List<Cargo> getAllSortedItems(TypeSortCargo typeSortCargo);

}
