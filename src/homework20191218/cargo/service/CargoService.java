package homework20191218.cargo.service;

import homework20191218.cargo.domain.Cargo;
import homework20191218.common.service.CommonService;

public interface CargoService extends CommonService {
    void add(Cargo cargo);

    Cargo[] getAll();

    Cargo[] getByName(String name);

    Cargo getById(Long id);

    boolean update(Cargo cargo);

    void sort();
    
    void sort(TypeSortCargo typeSortCargo);

}
