package homework20191227.cargo.repo;

import homework20191227.cargo.domain.Cargo;
import homework20191227.cargo.service.TypeSortCargo;
import homework20191227.common.business.repo.CommonRepo;

import java.util.List;

public interface CargoRepo extends CommonRepo<Cargo, Long> {

    Cargo getByIdFetchingTransportations(long id);

    List<Cargo> getByName(String name);

    List<Cargo> getAllSortedItems(TypeSortCargo typeSortCargo);

}
