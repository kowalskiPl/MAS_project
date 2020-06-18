package model.Parts;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.util.Date;

@Entity(name = "Engine")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Engine extends Part {
    protected double power;
    protected int minimumRotationSpeed;
    protected int maximumRotationSpeed;

    protected Engine() {
    }

    public Engine(String serialNumber, String manufacturer, int usageTime, Date creationDate, double power, int minimumRotationSpeed, int maximumRotationSpeed) {
        super(serialNumber, manufacturer, usageTime, creationDate);
        this.power = power;
        this.minimumRotationSpeed = minimumRotationSpeed;
        this.maximumRotationSpeed = maximumRotationSpeed;
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
}
