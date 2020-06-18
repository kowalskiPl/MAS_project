package model.Parts;

import javax.persistence.Entity;
import java.util.Date;

@Entity()
public class RotorBlade extends Part {
    private int maximumRotationSpeed;

    public RotorBlade() {
    }

    public RotorBlade(String serialNumber, String manufacturer, int usageTime, Date creationDate, int maximumRotationSpeed) {
        super(serialNumber, manufacturer, usageTime, creationDate);
        this.maximumRotationSpeed = maximumRotationSpeed;
    }

    public int getMaximumRotationSpeed() {
        return maximumRotationSpeed;
    }

    public void setMaximumRotationSpeed(int maximumRotationSpeed) {
        this.maximumRotationSpeed = maximumRotationSpeed;
    }
}
