package homework20191220.cargo.service;

import homework20191220.cargo.domain.Cargo;
import homework20191220.common.service.CommonService;

public interface CargoService extends CommonService {
    void add(Cargo cargo);

    Cargo[] getAll();

    Cargo[] getByName(String name);

    Cargo getById(Long id);

    boolean update(Cargo cargo);
    
    Cargo[] getAllSortedItems(TypeSortCargo typeSortCargo);

}
