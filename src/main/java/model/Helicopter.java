package model;

import model.Parts.RotorBlade;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "helicopter")
public class Helicopter extends Vehicle {

    @OneToMany(mappedBy = "helicopter", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<RotorBlade> rotorBlades = new ArrayList<>();

    public Helicopter() {
    }

    public Helicopter(String registrationNumber, Date assemblyDate, double maximumSpeed, double minimumSpeed,
                      double emptyWeight, double maximumWeight, double maximumG, int numberOfSeats) {
        super(registrationNumber, assemblyDate, maximumSpeed, minimumSpeed, emptyWeight, maximumWeight, maximumG, numberOfSeats);
    }

    public void addRotorBlade(RotorBlade blade){
        if (!rotorBlades.contains(blade)){
            rotorBlades.add(blade);
        }
    }

    public List<RotorBlade> getRotorBlades() {
        return rotorBlades;
    }

    public void setRotorBlades(List<RotorBlade> rotorBlades) {
        this.rotorBlades = rotorBlades;
    }
}
