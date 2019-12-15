package homework20191213.carrier.service;

import homework20191213.cargo.domain.Cargo;
import homework20191213.cargo.service.CargoService;

public class CarrierServiceImpl implements CargoService {
    @Override
    public void add(Cargo cargo) {
        
    }

    @Override
    public Cargo[] getAll() {
        return new Cargo[0];
    }

    @Override
    public Cargo[] getByName(String name) {
        return new Cargo[0];
    }

    @Override
    public Cargo getById(long id) {
        return null;
    }

    @Override
    public Cargo remove(long id) {
        return null;
    }
}
