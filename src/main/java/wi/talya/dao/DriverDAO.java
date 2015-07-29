package wi.talya.dao;

import wi.talya.model.Driver;

/**
 * Created by Таня on 23.07.2015.
 */
public interface DriverDAO {

    Driver getFreeDriver();
    Driver addDriver(Driver driver);
    void updateDriver(Driver driver);
}
