package model.Person;

import model.Vehicle;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Entity(name = "client")
public class Client extends Person {

    private String phoneNumber;

    @OneToOne(mappedBy="client", cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy = "client", orphanRemoval = true, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Vehicle> vehicles = new TreeSet<>();

    public Client() {
    }

    public Client(String firstName, String secondName, String phoneNumber, Address address) {
        super(firstName, secondName);
        this.phoneNumber = phoneNumber;
        this.address = address;
        address.addClient(this);
    }

    public void addAddress(Address address){
        if (this.address == null){
            this.address = address;
            address.addClient(this);
        }
    }

    public void addVehicle(Vehicle vehicle){
        if (!vehicles.contains(vehicle)){
            vehicles.add(vehicle);
            vehicle.addClient(this);
        }
    }

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
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

    @Override
    public String toString() {
        return "Client{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", address=" + address +
                ", vehicles=" + vehicles +
                ", id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
