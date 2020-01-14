package main.homework20191220.cargo.service;

import main.homework20191220.cargo.domain.Cargo;
import main.homework20191220.common.service.CommonService;

public interface CargoService extends CommonService {
    void add(Cargo cargo);

    Cargo[] getAll();

    Cargo[] getByName(String name);

    Cargo getById(Long id);

    boolean update(Cargo cargo);
    
    Cargo[] getAllSortedItems(TypeSortCargo typeSortCargo);

}
