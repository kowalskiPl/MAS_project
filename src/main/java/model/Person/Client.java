package model.Person;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity(name = "model.Person.Client")
public class Client extends Person {

    private String phoneNumber;

    @OneToOne(mappedBy="model.Person.Client", cascade = CascadeType.ALL)
    private Address address;

    public Client() {
    }

    public Client(String firstName, String secondName, String phoneNumber, Address address) {
        super(firstName, secondName);
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
