package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "manufacturer")
public class Manufacturer {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    protected long id;

    private String name;

    private String contactNumber;

    @OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vehicle> vehicles = new ArrayList<>();

    public Manufacturer() {
    }

    public Manufacturer(String name, String contactNumber) {
        this.name = name;
        this.contactNumber = contactNumber;
    }

    public void addVehicle(Vehicle vehicle){
        if (!vehicles.contains(vehicle)){
            vehicles.add(vehicle);
            vehicle.addManufacturer(this);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
