import db_util.HibernateDBUtil;
import db_util.HibernateUtil;
import logging.LogPolicy;
import logging.Logger;
import logging.SeverityType;
import model.Airplane;
import model.Helicopter;
import model.Parts.GasTurbineEngine;
import model.Person.Address;
import model.Person.Client;
import model.ServiceSummary;
import org.hibernate.Session;
import org.junit.Test;

import java.util.Date;

/**
 * A meme class containing meme tests that have nothing in common with junit test
 */
public class Tests {

    private void setupLogger(){
        Logger.getInstance().initialize(new LogPolicy(), "testLog.txt");
    }

    private void shutdownLogger(){
        Logger.getInstance().deinitialize();
    }

    @Test
    public void engineTest(){
        setupLogger();
        Airplane airplane = new Airplane();
        GasTurbineEngine engine = GasTurbineEngine.getEngine(airplane,"XS4533SDG", "GE", 0, new Date(),
                1500, 1200, 8240, 230);
        GasTurbineEngine engine2 = GasTurbineEngine.getEngine(airplane,"AW4333SMN", "BMW", 0, new Date(),
                1500, 1200, 8240, 230);

        System.out.println(airplane);
        shutdownLogger();
    }

    @Test
    public void hibernateBasicTest(){
        setupLogger();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Address address = new Address("Szlifierska 21/31", "Warsaw", "01-461");
        Client client = new Client("John", "Doe", "608810369", address);
        session.save(address);
        session.save(client);
        session.getTransaction().commit();
        session.close();
        shutdownLogger();
    }

    @Test
    public void multipleVehicleAddTest(){
        setupLogger();
        Address address = new Address("Szlifierska 21/31", "Warsaw", "01-461");
        Client client = new Client("John", "Doe", "608810369", address);
        HibernateDBUtil.saveNewClient(client, address);
        Airplane airplane = new Airplane();
        Helicopter helicopter = new Helicopter();
        HibernateDBUtil.addVehicleToClient(1, airplane);
        HibernateDBUtil.addVehicleToClient(1, helicopter);
        System.out.println(HibernateDBUtil.getClient(1));
        shutdownLogger();
    }

    @Test
    public void serviceSummaryTst(){
        setupLogger();
        Address address = new Address("Szlifierska 21/31", "Warsaw", "01-461");
        Client client = new Client("John", "Doe", "608810369", address);
        HibernateDBUtil.saveNewClient(client, address);
        Airplane airplane = new Airplane();
        airplane.setRegistrationNumber("EF-ABC");
        Helicopter helicopter = new Helicopter();
        HibernateDBUtil.addVehicleToClient(1, airplane);
        HibernateDBUtil.addVehicleToClient(1, helicopter);

        ServiceSummary serviceSummary = new ServiceSummary(new Date(), "test", "Test description");
        HibernateDBUtil.saveVehicleServiceSummary(1, serviceSummary);
        Logger.getInstance().print(HibernateDBUtil.searchServiceSummaries("EF-ABC").toString(), SeverityType.INFO);
        shutdownLogger();
    }
}
