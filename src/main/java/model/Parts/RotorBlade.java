package model.Parts;

import model.Helicopter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity(name = "rotorBlade")
public class RotorBlade extends Part {

    private int maximumRotationSpeed;

    @ManyToOne
    private Helicopter helicopter;

    private RotorBlade() {
    }

    private RotorBlade(String serialNumber, String manufacturer, int usageTime, Date creationDate, int maximumRotationSpeed) {
        super(serialNumber, manufacturer, usageTime, creationDate);
        this.maximumRotationSpeed = maximumRotationSpeed;
    }

    public static void addHelicopter(Helicopter helicopter, String serialNumber, String manufacturer, int usageTime, Date creationDate, int maximumRotationSpeed) {
        if (helicopter == null) {
            throw new IllegalArgumentException("Given helicopter does not exist");
        } else {
            RotorBlade blade = new RotorBlade(serialNumber, manufacturer, usageTime, creationDate, maximumRotationSpeed);
            helicopter.addRotorBlade(blade);
        }
    }

    public int getMaximumRotationSpeed() {
        return maximumRotationSpeed;
    }

    public void setMaximumRotationSpeed(int maximumRotationSpeed) {
        this.maximumRotationSpeed = maximumRotationSpeed;
    }

    public Helicopter getHelicopter() {
        return helicopter;
    }

    public void setHelicopter(Helicopter helicopter) {
        this.helicopter = helicopter;
    }
}
