package homework20191230.cargo.repo;

import homework20191230.cargo.domain.Cargo;
import homework20191230.cargo.service.TypeSortCargo;
import homework20191230.common.business.repo.CommonRepo;

import java.util.List;

public interface CargoRepo extends CommonRepo<Cargo, Long> {

    Cargo getByIdFetchingTransportations(long id);

    List<Cargo> getByName(String name);

    List<Cargo> getAllSortedItems(TypeSortCargo typeSortCargo);

}
