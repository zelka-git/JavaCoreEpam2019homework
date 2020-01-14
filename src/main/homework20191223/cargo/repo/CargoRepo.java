package main.homework20191223.cargo.repo;

import main.homework20191223.cargo.domain.Cargo;
import main.homework20191223.cargo.service.TypeSortCargo;
import main.homework20191223.common.business.repo.CommonRepo;

import java.util.List;

public interface CargoRepo extends CommonRepo<Cargo> {

    Cargo getByIdFetchingTransportations(long id);

    List<Cargo> getByName(String name);

    List<Cargo> getAllSortedItems(TypeSortCargo typeSortCargo);

}
