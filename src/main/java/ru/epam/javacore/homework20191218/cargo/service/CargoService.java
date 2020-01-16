package ru.epam.javacore.homework20191218.cargo.service;

import ru.epam.javacore.homework20191218.cargo.domain.Cargo;
import ru.epam.javacore.homework20191218.common.service.CommonService;

public interface CargoService extends CommonService {
    void add(Cargo cargo);

    Cargo[] getAll();

    Cargo[] getByName(String name);

    Cargo getById(Long id);

    boolean update(Cargo cargo);
    
    Cargo[] getAllSortedItems(TypeSortCargo typeSortCargo);

}
