package model.Parts;

import javax.persistence.Entity;
import java.util.Date;

@Entity(name = "PistonEngine")
public class PistonEngine extends Engine {
    private int pistonCount;
    private double nominalOilPressure;

    public PistonEngine() {
    }

    public PistonEngine(String serialNumber, String manufacturer, int usageTime, Date creationDate,
                        double power, int minimumRotationSpeed, int maximumRotationSpeed, int pistonCount,
                        double nominalOilPressure) {
        super(serialNumber, manufacturer, usageTime, creationDate, power, minimumRotationSpeed, maximumRotationSpeed);
        this.pistonCount = pistonCount;
        this.nominalOilPressure = nominalOilPressure;
    }

    public int getPistonCount() {
        return pistonCount;
    }

    public void setPistonCount(int pistonCount) {
        this.pistonCount = pistonCount;
    }

    public double getNominalOilPressure() {
        return nominalOilPressure;
    }

    public void setNominalOilPressure(double nominalOilPressure) {
        this.nominalOilPressure = nominalOilPressure;
    }
}
