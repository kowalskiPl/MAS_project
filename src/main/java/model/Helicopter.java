package model;

import model.Parts.RotorBlade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.*;

@Entity(name = "helicopter")
public class Helicopter extends Vehicle {

    @OneToMany(mappedBy = "helicopter", orphanRemoval = true, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<RotorBlade> rotorBlades = new TreeSet<>();

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

    public Set<RotorBlade> getRotorBlades() {
        return rotorBlades;
    }

    public void setRotorBlades(Set<RotorBlade> rotorBlades) {
        this.rotorBlades = rotorBlades;
    }
}
