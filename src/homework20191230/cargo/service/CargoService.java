package homework20191230.cargo.service;

import homework20191230.cargo.domain.Cargo;
import homework20191230.common.business.service.CommonService;

import java.util.List;

public interface CargoService extends CommonService<Cargo, Long> {

    List<Cargo> getByName(String name);
    
    Cargo getByIdFetchingTransportations(Long id);

    List<Cargo> getAllSortedItems(TypeSortCargo typeSortCargo);

}
