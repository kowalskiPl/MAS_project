package model.Person;

import javax.persistence.Entity;

@Entity(name = "model.Person.Employee")
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
