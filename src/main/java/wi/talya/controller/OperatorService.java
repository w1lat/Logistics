package wi.talya.controller;

import wi.talya.model.Cargo;
import wi.talya.model.Driver;
import wi.talya.model.WayBill;

import java.util.List;
import java.util.Map;


public interface OperatorService {

    public int addCargo(Cargo ... cargo);
    public WayBill formWayBill();
    public Cargo findById(Long id);
    public List<Cargo> getAll();
    public Cargo removeFromDb(long id);
}
