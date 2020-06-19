package model.Person;

import javax.persistence.Entity;

@Entity(name = "juniorEngineer")
public class JuniorEngineer extends Employee {
    public static int experienceForSeniority = 4;

    public JuniorEngineer() {
    }

    public JuniorEngineer(String firstName, String secondName, double salary, int experience) {
        super(firstName, secondName, salary, experience);
    }
}
