package homework20191223.cargo.service;

import homework20191223.cargo.domain.Cargo;
import homework20191223.common.business.service.CommonService;

import java.util.List;

public interface CargoService extends CommonService<Cargo> {

    List<Cargo> getByName(String name);
    
    Cargo getByIdFetchingTransportations(Long id);

    List<Cargo> getAllSortedItems(TypeSortCargo typeSortCargo);

}
