package db_util;

import model.Person.Address;
import model.Person.Client;
import model.Person.SeniorEngineer;
import model.ServiceSummary;
import model.Vehicle;
import org.hibernate.Session;

import java.util.List;

/**
 * A class handling database operations
 */
public class HibernateDBUtil {

    public static void saveNewClient(Client client, Address address) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            client.addAddress(address);
            session.save(client);
            session.save(address);
            session.getTransaction().commit();
        }
    }

    public static Client getClient(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from client cl where cl.id = : id", Client.class)
                    .setParameter("id", id)
                    .list().get(0);
        }
    }

    public static void saveVehicleServiceSummary(long vehicleId, ServiceSummary summary) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Vehicle vehicle = session.createQuery("from vehicle vc where vc.id =: id", Vehicle.class)
                    .setParameter("id", vehicleId).list().get(0);
            vehicle.addServiceSummary(summary);
            session.update(vehicle);
            session.save(summary);
            session.getTransaction().commit();
        }
    }

    public static void updateServiceSummary(long summaryId, String title, String description, boolean approved) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            ServiceSummary summary = session.createQuery("from serviceSummary ss where ss.id =: id", ServiceSummary.class)
                    .setParameter("id", summaryId).list().get(0);
            summary.setTitle(title);
            summary.setDescription(description);
            summary.setApproved(approved);
            session.update(summary);
            session.getTransaction().commit();
        }
    }

    public static void saveSeniorEngineer(SeniorEngineer engineer) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(engineer);
            session.getTransaction().commit();
        }
    }

    public static void addVehicleToClient(long clientId, Vehicle vehicle) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Client client = session.createQuery("from client cl where cl.id = : id", Client.class).
                    setParameter("id", clientId).list().get(0);
            client.addVehicle(vehicle);
            session.update(client);
            session.save(vehicle);
            session.getTransaction().commit();
        }
    }
}
