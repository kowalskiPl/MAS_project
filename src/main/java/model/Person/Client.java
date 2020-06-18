package model.Person;

import model.Vehicle;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "client")
public class Client extends Person {

    private String phoneNumber;

    @OneToOne(mappedBy="model.Person.Client", cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy = "client", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Vehicle> vehicles = new ArrayList<>();

    public Client() {
    }

    public Client(String firstName, String secondName, String phoneNumber, Address address) {
        super(firstName, secondName);
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public void addVehicle(Vehicle vehicle){
        if (!vehicles.contains(vehicle)){
            vehicles.add(vehicle);
            vehicle.addClient(this);
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
