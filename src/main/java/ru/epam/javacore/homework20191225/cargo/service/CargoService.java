package ru.epam.javacore.homework20191225.cargo.service;

import ru.epam.javacore.homework20191225.cargo.domain.Cargo;
import ru.epam.javacore.homework20191225.common.business.service.CommonService;

import java.util.List;

public interface CargoService extends CommonService<Cargo, Long> {

    List<Cargo> getByName(String name);
    
    Cargo getByIdFetchingTransportations(Long id);

    List<Cargo> getAllSortedItems(TypeSortCargo typeSortCargo);

}
