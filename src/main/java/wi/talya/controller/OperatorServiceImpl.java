package wi.talya.controller;

import wi.talya.dao.CargoDAO;
import wi.talya.dao.DriverDAO;
import wi.talya.model.Cargo;
import wi.talya.model.CargoState;
import wi.talya.model.Driver;
import wi.talya.model.WayBill;

import java.util.List;
import java.util.Map;

/**
 * Created by Таня on 15.07.2015.
 */
public class OperatorServiceImpl implements OperatorService {

    private CargoDAO cargoDAO;
    private DriverDAO driverDAO;

    public OperatorServiceImpl(CargoDAO cargoDAO, DriverDAO driverDAO) {
        this.cargoDAO = cargoDAO;
        this.driverDAO = driverDAO;
    }

    public int addCargo(Cargo ... cargo) {
        int i = 0;
        for(Cargo c : cargo) {
            if(cargoDAO.addCargo(c) != null)
            i++;
        }
        return i;
    }

    public WayBill formWayBill() {
        Driver driver = driverDAO.getFreeDriver();
        int freeVolume = driver.getCarVolume();
        WayBill wayBill = new WayBill(driver);

        List<Cargo> cargoList = cargoDAO.getAllToDeliver();
        for (Cargo c : cargoList){
            if((freeVolume > 0) && ((freeVolume - c.getVolume()) >= 0)){
                wayBill.addCargoToWayBill(c);
                c.setState(CargoState.INTRANSIT);
                c.setWayBill(wayBill);
//                cargoDAO.updateCargo(c);
                freeVolume -= c.getVolume();
            }
        }
        driver.setWayBill(wayBill);
        driver.setBusy(true);
        if(wayBill.getListSize() > 0)
            driverDAO.updateDriver(driver);

        return wayBill;
    }

    public Cargo findById(Long id) {
        return cargoDAO.getById(id);
    }

    public List<Cargo> getAll() {
        return cargoDAO.getAll();
    }

    public Cargo removeFromDb(long id) {
        return cargoDAO.removeCargo(id);
    }


}
