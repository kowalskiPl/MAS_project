package model;

import model.Parts.Engine;
import model.Parts.NavigationSystem;
import model.Person.Client;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity(name = "vehicle")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Vehicle {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    protected long id;

    protected String registrationNumber;

    @Temporal(TemporalType.DATE)
    protected Date assemblyDate;

    protected double maximumSpeed;

    protected double minimumSpeed;

    protected double emptyWeight;

    protected double maximumWeight;

    protected double maximumG;

    protected int numberOfSeats;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    protected List<ServiceSummary> serviceSummaries = new ArrayList<>();

    @ManyToOne
    protected Manufacturer manufacturer = null;

    @ManyToOne
    protected Client client = null;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    protected List<Engine> engines = new ArrayList<>();

    @OneToOne()
    protected NavigationSystem navigationSystem;

    public Vehicle() {
    }

    public Vehicle(String registrationNumber, Date assemblyDate, double maximumSpeed, double minimumSpeed,
                   double emptyWeight, double maximumWeight, double maximumG, int numberOfSeats) {
        this.registrationNumber = registrationNumber;
        this.assemblyDate = assemblyDate;
        this.maximumSpeed = maximumSpeed;
        this.minimumSpeed = minimumSpeed;
        this.emptyWeight = emptyWeight;
        this.maximumWeight = maximumWeight;
        this.maximumG = maximumG;
        this.numberOfSeats = numberOfSeats;
    }

    public void addManufacturer(Manufacturer manufacturer) {
        if (this.manufacturer == null) {
            this.manufacturer = manufacturer;
            manufacturer.addVehicle(this);
        }
    }

    public void addClient(Client client) {
        if (this.client == null) {
            this.client = client;
            client.addVehicle(this);
        }
    }

    public void addEngine(Engine engine) {
        engines.add(engine);
    }

    public void addNavigationSystem(NavigationSystem navigationSystem) {
        if (this.navigationSystem == null) {
            this.navigationSystem = navigationSystem;
            navigationSystem.addVehicle(this);
        }
    }

    public List<ServiceSummary> getServiceSummaries() {
        return serviceSummaries;
    }

    public void setServiceSummaries(List<ServiceSummary> serviceSummaries) {
        this.serviceSummaries = serviceSummaries;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Engine> getEngines() {
        return engines;
    }

    public void setEngines(List<Engine> engines) {
        this.engines = engines;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Date getAssemblyDate() {
        return assemblyDate;
    }

    public void setAssemblyDate(Date assemblyDate) {
        this.assemblyDate = assemblyDate;
    }

    public double getMaximumSpeed() {
        return maximumSpeed;
    }

    public void setMaximumSpeed(double maximumSpeed) {
        this.maximumSpeed = maximumSpeed;
    }

    public double getMinimumSpeed() {
        return minimumSpeed;
    }

    public void setMinimumSpeed(double minimumSpeed) {
        this.minimumSpeed = minimumSpeed;
    }

    public double getEmptyWeight() {
        return emptyWeight;
    }

    public void setEmptyWeight(double emptyWeight) {
        this.emptyWeight = emptyWeight;
    }

    public double getMaximumWeight() {
        return maximumWeight;
    }

    public void setMaximumWeight(double maximumWeight) {
        this.maximumWeight = maximumWeight;
    }

    public double getMaximumG() {
        return maximumG;
    }

    public void setMaximumG(double maximumG) {
        this.maximumG = maximumG;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", assemblyDate=" + assemblyDate +
                ", maximumSpeed=" + maximumSpeed +
                ", minimumSpeed=" + minimumSpeed +
                ", emptyWeight=" + emptyWeight +
                ", maximumWeight=" + maximumWeight +
                ", maximumG=" + maximumG +
                ", numberOfSeats=" + numberOfSeats +
                ", serviceSummaries=" + serviceSummaries +
                ", manufacturer=" + manufacturer +
                ", client=" + client +
                ", engines=" + engines +
                '}';
    }
}
