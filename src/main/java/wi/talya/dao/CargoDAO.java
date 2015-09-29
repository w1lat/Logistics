package wi.talya.dao;

import wi.talya.model.Cargo;

import java.util.List;


public interface CargoDAO {

    public Cargo addCargo(Cargo cargo);
    public void updateCargo(Cargo cargo);
    public Cargo removeCargo(long id);
    public Cargo getById(long id);
    public List<Cargo> getAll();
    public List<Cargo> getAllToDeliver();

}
