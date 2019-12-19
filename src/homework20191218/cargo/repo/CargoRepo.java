package homework20191218.cargo.repo;

import homework20191218.cargo.domain.Cargo;
import homework20191218.common.repo.CommonRepo;

import java.util.Comparator;

public interface CargoRepo extends CommonRepo {
    void add(Cargo cargo);

    Cargo[] getAll();

    Cargo[] getByName(String name);

    Cargo getById(long id);

    boolean update(Cargo cargo);

    void sort(Comparator comparator);
}
