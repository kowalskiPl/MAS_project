package model.Parts;

import javax.persistence.Entity;
import java.util.Date;

@Entity(name = "GasTurbineEngine")
public class GasTurbineEngine extends Engine {
    private int idleTemperature;

    public GasTurbineEngine() {
    }

    public GasTurbineEngine(String serialNumber, String manufacturer, int usageTime, Date creationDate, double power,
                            int minimumRotationSpeed, int maximumRotationSpeed, int idleTemperature) {
        super(serialNumber, manufacturer, usageTime, creationDate, power, minimumRotationSpeed, maximumRotationSpeed);
        this.idleTemperature = idleTemperature;
    }

    public int getIdleTemperature() {
        return idleTemperature;
    }

    public void setIdleTemperature(int idleTemperature) {
        this.idleTemperature = idleTemperature;
    }
}
