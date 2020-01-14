package main.homework20191225.cargo.service;

import main.homework20191225.cargo.domain.Cargo;
import main.homework20191225.common.business.service.CommonService;

import java.util.List;

public interface CargoService extends CommonService<Cargo, Long> {

    List<Cargo> getByName(String name);
    
    Cargo getByIdFetchingTransportations(Long id);

    List<Cargo> getAllSortedItems(TypeSortCargo typeSortCargo);

}
