package ru.epam.javacore.homework20200127.cargo.service;

import ru.epam.javacore.homework20200127.cargo.domain.Cargo;
import ru.epam.javacore.homework20200127.cargo.search.CargoSearchCondition;
import ru.epam.javacore.homework20200127.cargo.service.TypeSortCargo;
import ru.epam.javacore.homework20200127.common.business.service.CommonService;

import java.io.Serializable;
import java.util.List;

public interface CargoService extends CommonService<Cargo, Long>, Serializable {

    List<Cargo> getByName(String name);
    
    Cargo getByIdFetchingTransportations(Long id);

    List<Cargo> getAllSortedItems(TypeSortCargo typeSortCargo);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);

}
