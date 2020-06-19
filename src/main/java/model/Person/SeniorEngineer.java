package model.Person;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity(name = "seniorEngineer")
public class SeniorEngineer extends Employee {

    public enum Specialization {
        airplanes, helicopters
    }

    public SeniorEngineer() {
    }

    @Enumerated(EnumType.STRING)
    private Specialization specialization;

    public SeniorEngineer(String firstName, String secondName, double salary, int experience, Specialization specialization) {
        super(firstName, secondName, salary, experience);
        this.specialization = specialization;
    }

    public SeniorEngineer(Employee previousEmployee, Specialization specialization) {
        super();
        if (previousEmployee.experience < JuniorEngineer.experienceForSeniority) {
            throw new IllegalArgumentException("Required experience condition not met!");
        } else {
            this.firstName = previousEmployee.firstName;
            this.lastName = previousEmployee.lastName;
            this.experience = previousEmployee.experience;
            this.salary = previousEmployee.salary;
            this.specialization = specialization;
        }
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }
}
