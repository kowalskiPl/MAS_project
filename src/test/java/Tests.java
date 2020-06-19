import db_util.HibernateDBUtil;
import db_util.HibernateUtil;
import model.Airplane;
import model.Helicopter;
import model.Parts.GasTurbineEngine;
import model.Person.Address;
import model.Person.Client;
import org.hibernate.Session;
import org.junit.Test;

import java.util.Date;

public class Tests {

    @Test
    public void engineTest(){
        Airplane airplane = new Airplane();
        GasTurbineEngine engine = GasTurbineEngine.getEngine(airplane,"XS4533SDG", "GE", 0, new Date(),
                1500, 1200, 8240, 230);
        GasTurbineEngine engine2 = GasTurbineEngine.getEngine(airplane,"AW4333SMN", "BMW", 0, new Date(),
                1500, 1200, 8240, 230);

        System.out.println(airplane);
    }

    @Test
    public void hibernateBasicTest(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Address address = new Address("Szlifierska 21/31", "Warsaw", "01-461");
        Client client = new Client("John", "Doe", "608810369", address);
        session.save(address);
        session.save(client);
        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void multipleVehicleAddTest(){
        Address address = new Address("Szlifierska 21/31", "Warsaw", "01-461");
        Client client = new Client("John", "Doe", "608810369", address);
        HibernateDBUtil.saveNewClient(client, address);
        Airplane airplane = new Airplane();
        Helicopter helicopter = new Helicopter();
        HibernateDBUtil.addVehicleToClient(1, airplane);
        HibernateDBUtil.addVehicleToClient(1, helicopter);
        System.out.println(HibernateDBUtil.getClient(1));
    }
}
