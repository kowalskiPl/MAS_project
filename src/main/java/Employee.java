import javax.persistence.Entity;

@Entity(name = "Employee")
public class Employee extends Person{
    protected double salary;
    protected int experience;

    public Employee() {
    }

    public Employee(String firstName, String secondName, double salary, int experience) {
        super(firstName, secondName);
        this.salary = salary;
        this.experience = experience;
    }
}
