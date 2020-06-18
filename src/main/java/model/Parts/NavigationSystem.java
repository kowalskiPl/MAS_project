package model.Parts;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "navigationSystem")
public class NavigationSystem extends Part {

    public enum DisplayType {
        LCD, CRT
    }

    @Temporal(TemporalType.DATE)
    private Date lastSoftwareUpdate;

    @Enumerated(EnumType.STRING)
    private DisplayType displayType;

    private double heightMeasurementPrecision;

    private double horizontalLocationMeasurementPrecision;

    @Temporal(TemporalType.DATE)
    private Date lastCalibration;

    public NavigationSystem() {
    }

    public NavigationSystem(String serialNumber, String manufacturer, int usageTime, Date creationDate,
                            Date lastSoftwareUpdate, DisplayType displayType, double heightMeasurementPrecision,
                            double horizontalLocationMeasurementPrecision, Date lastCalibration) {
        super(serialNumber, manufacturer, usageTime, creationDate);
        this.lastSoftwareUpdate = lastSoftwareUpdate;
        this.displayType = displayType;
        this.heightMeasurementPrecision = heightMeasurementPrecision;
        this.horizontalLocationMeasurementPrecision = horizontalLocationMeasurementPrecision;
        this.lastCalibration = lastCalibration;
    }

    public Date getLastSoftwareUpdate() {
        return lastSoftwareUpdate;
    }

    public void setLastSoftwareUpdate(Date lastSoftwareUpdate) {
        this.lastSoftwareUpdate = lastSoftwareUpdate;
    }

    public DisplayType getDisplayType() {
        return displayType;
    }

    public void setDisplayType(DisplayType displayType) {
        this.displayType = displayType;
    }

    public double getHeightMeasurementPrecision() {
        return heightMeasurementPrecision;
    }

    public void setHeightMeasurementPrecision(double heightMeasurementPrecision) {
        this.heightMeasurementPrecision = heightMeasurementPrecision;
    }

    public double getHorizontalLocationMeasurementPrecision() {
        return horizontalLocationMeasurementPrecision;
    }

    public void setHorizontalLocationMeasurementPrecision(double horizontalLocationMeasurementPrecision) {
        this.horizontalLocationMeasurementPrecision = horizontalLocationMeasurementPrecision;
    }

    public Date getLastCalibration() {
        return lastCalibration;
    }

    public void setLastCalibration(Date lastCalibration) {
        this.lastCalibration = lastCalibration;
    }
}
