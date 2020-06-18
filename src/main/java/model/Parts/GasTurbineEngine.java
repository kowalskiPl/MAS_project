package model.Parts;

import model.Vehicle;

import javax.persistence.Entity;
import java.util.Date;

@Entity(name = "GasTurbineEngine")
public class GasTurbineEngine extends Engine {
    private int idleTemperature;

    private GasTurbineEngine() {
    }

    private GasTurbineEngine(Engine engine, int idleTemperature){
        super(engine.serialNumber, engine.manufacturer, engine.usageTime, engine.creationDate, engine.power,
                engine.minimumRotationSpeed, engine.maximumRotationSpeed);
        this.idleTemperature = idleTemperature;
    }

    private GasTurbineEngine(String serialNumber, String manufacturer, int usageTime, Date creationDate, double power,
                            int minimumRotationSpeed, int maximumRotationSpeed, int idleTemperature) {
        super(serialNumber, manufacturer, usageTime, creationDate, power, minimumRotationSpeed, maximumRotationSpeed);
        this.idleTemperature = idleTemperature;
    }

    public static GasTurbineEngine getEngine(Vehicle vehicle, String serialNumber, String manufacturer, int usageTime, Date creationDate,
                                   double power, int minimumRotationSpeed, int maximumRotationSpeed, int idleTemperature) {
        return new GasTurbineEngine(Engine.getEngine(vehicle, serialNumber, manufacturer, usageTime, creationDate, power,
                minimumRotationSpeed, maximumRotationSpeed), idleTemperature);
    }

    public int getIdleTemperature() {
        return idleTemperature;
    }

    public void setIdleTemperature(int idleTemperature) {
        this.idleTemperature = idleTemperature;
    }

    @Override
    public String toString() {
        return "GasTurbineEngine{" +
                "idleTemperature=" + idleTemperature +
                ", power=" + power +
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
