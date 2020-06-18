package model;

import javax.persistence.Entity;
import java.util.Date;

@Entity(name = "airplane")
public class Airplane extends Vehicle{
    private double ascentRate;

    public Airplane() {
    }

    public Airplane(String registrationNumber, Date assemblyDate, double maximumSpeed,
                    double minimumSpeed, double emptyWeight, double maximumWeight, double maximumG,
                    int numberOfSeats, double ascentRate) {
        super(registrationNumber, assemblyDate, maximumSpeed, minimumSpeed, emptyWeight, maximumWeight, maximumG, numberOfSeats);
        this.ascentRate = ascentRate;
    }

    public double getAscentRate() {
        return ascentRate;
    }

    public void setAscentRate(double ascentRate) {
        this.ascentRate = ascentRate;
    }
}
