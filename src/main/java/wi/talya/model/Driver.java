package wi.talya.model;


import javax.persistence.*;

/**
 * Created by Таня on 02.06.2015.
 */

@Entity
@Table(name = "drivers")
public class Driver extends GeneratedIdentifierEntity{

    @Column(name = "full_name", length = 30)
    private String full_name;
    @Column(name = "car_volume")
    private int carVolume;


    @Column
    private boolean busy = false;
    @OneToOne(mappedBy = "driver",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    private WayBill wayBill;
    public Driver() {
    }

    public Driver(String name, int carVolume) {
        this.full_name = name;
        this.carVolume = carVolume;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String name) {
        this.full_name = name;
    }

    public int getCarVolume() {
        return carVolume;
    }

    public void setCarVolume(int carVolume) {
        this.carVolume = carVolume;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    public WayBill getWayBill() {
        return wayBill;
    }

    public void setWayBill(WayBill wayBill) {
        this.wayBill = wayBill;
    }
}
