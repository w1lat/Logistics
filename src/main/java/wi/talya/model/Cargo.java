package wi.talya.model;

import javax.persistence.*;

/**
 * Created by Таня on 02.06.2015.
 */

@Entity
@Table(name = "cargo")
public class Cargo extends GeneratedIdentifierEntity{

    @Column
    private String name;
    @Column
    private int volume;
    @Column
    private String destination;
    @Column(length = 10, nullable = false)
    private String receiverId;
    @Enumerated(EnumType.STRING)
    private CargoState state;
    @ManyToOne
    @JoinColumn(name = "way_bill_id", referencedColumnName = "id")
    private WayBill wayBill;

    public Cargo() {
    }

    public Cargo(String name, long id, int volume, String destination) {
        this.name = name;
        this.volume = volume;
        this.destination = destination;
        this.state = CargoState.OFSTOCK;
    }

    public WayBill getWayBill() {
        return wayBill;
    }

    public void setWayBill(WayBill wayBill) {
        this.wayBill = wayBill;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiver) {
        this.receiverId = receiver;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getVolume() {
        return volume;
    }

    public String getDestination() {
        return destination;
    }

    public CargoState getState() {
        return state;
    }

    public void setState(CargoState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return String.format("%10s%6d%3d%10s%10s", name, id, volume, destination,  receiverId);
    }
}
