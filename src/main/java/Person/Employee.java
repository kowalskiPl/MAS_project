package Person;

import javax.persistence.Entity;

@Entity(name = "Person.Employee")
public class Employee extends Person {
    protected double salary;

    protected int experience;

    protected Employee() {
    }

    protected Employee(String firstName, String secondName, double salary, int experience) {
        super(firstName, secondName);
        this.salary = salary;
        this.experience = experience;
    }
}
