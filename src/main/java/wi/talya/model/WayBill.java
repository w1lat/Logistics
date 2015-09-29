package wi.talya.model;

import com.sun.javafx.binding.StringFormatter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


@Entity
@Table(name = "way_bills")
public class WayBill extends GeneratedIdentifierEntity{

    @OneToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    private Driver driver;

    @OneToMany(mappedBy = "wayBill",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    private List<Cargo> cargoList = new ArrayList<Cargo>();

    public WayBill() {
    }

    public WayBill(Driver driver) {
        this.driver = driver;
    }

    public WayBill(Driver driver, List<Cargo> cargoList) {
        this.driver = driver;
        this.cargoList.addAll(cargoList);
    }

    public int getListSize(){
        return cargoList.size();
    }

    public void addCargoToWayBill(Cargo cargo){
        cargoList.add(cargo);
    }

    public void addCargoToWayBill(List<Cargo> cargoList){
        this.cargoList.addAll(cargoList);
    }

    public long getId() {
        return id;
    }

    public Driver getDriver() {
        return driver;
    }

    @Override
    public String toString() {
        return String.format("%6d %15s ", id, driver.getFull_name() +
                cargoList.toString());
    }
}
