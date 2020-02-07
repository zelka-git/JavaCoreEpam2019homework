package ru.epam.javacore.homework20200205.cargo.service;

import ru.epam.javacore.homework20200205.cargo.domain.Cargo;
import ru.epam.javacore.homework20200205.cargo.search.CargoSearchCondition;
import ru.epam.javacore.homework20200205.cargo.service.TypeSortCargo;
import ru.epam.javacore.homework20200205.common.business.service.CommonService;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface CargoService extends CommonService<Cargo, Long>, Serializable {

    List<Cargo> getByName(String name);
    
    Optional<Cargo> getByIdFetchingTransportations(Long id);

    List<Cargo> getAllSortedItems(TypeSortCargo typeSortCargo);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);

}
