package wi.talya.dao;

import wi.talya.model.Driver;


public interface DriverDAO {

    Driver getFreeDriver();
    Driver addDriver(Driver driver);
    void updateDriver(Driver driver);
}
