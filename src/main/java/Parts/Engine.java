package Parts;

import javax.persistence.Entity;
import java.util.Date;

@Entity(name = "Engine")
public class Engine extends Part {
    protected int minimumRotationSpeed;
    protected int maximumRotationSpeed;

    protected Engine() {
    }

    protected Engine(String serialNumber, String manufacturer, int usageTime, Date creationDate, int minimumRotationSpeed, int maximumRotationSpeed) {
        super(serialNumber, manufacturer, usageTime, creationDate);
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
}
