package model.Parts;

import model.Vehicle;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity(name = "Engine")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Engine extends Part{

    protected double power;

    protected int minimumRotationSpeed;

    protected int maximumRotationSpeed;

    @ManyToOne
    protected Vehicle vehicle = null;

    protected Engine() {
    }

    protected Engine(String serialNumber, String manufacturer, int usageTime, Date creationDate, double power, int minimumRotationSpeed, int maximumRotationSpeed) {
        super(serialNumber, manufacturer, usageTime, creationDate);
        this.power = power;
        this.minimumRotationSpeed = minimumRotationSpeed;
        this.maximumRotationSpeed = maximumRotationSpeed;
    }

    protected static Engine getEngine(Vehicle vehicle, String serialNumber, String manufacturer, int usageTime, Date creationDate,
                                   double power, int minimumRotationSpeed, int maximumRotationSpeed) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Given vehicle does not exit!");
        } else {
            Engine engine = new Engine(serialNumber, manufacturer, usageTime, creationDate,
                    power, minimumRotationSpeed, maximumRotationSpeed);
            vehicle.addEngine(engine);
            engine.setVehicle(vehicle);
            return engine;
        }
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public int getMinimumRotationSpeed() {
        return minimumRotationSpeed;
    }

    public void setMinimumRotationSpeed(int minimumRotationSpeed) {
        this.minimumRotationSpeed = minimumRotationSpeed;
    }

    public int getMaximumRotationSpeed() {
        return maximumRotationSpeed;
    }

    public void setMaximumRotationSpeed(int maximumRotationSpeed) {
        this.maximumRotationSpeed = maximumRotationSpeed;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "power=" + power +
                ", minimumRotationSpeed=" + minimumRotationSpeed +
                ", maximumRotationSpeed=" + maximumRotationSpeed +
                ", id=" + id +
                ", creationDate=" + creationDate +
                ", serialNumber='" + serialNumber + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", usageTime=" + usageTime +
                '}';
    }
}
