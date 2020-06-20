package db_util;

import logging.Logger;
import logging.SeverityType;
import model.Person.Address;
import model.Person.Client;
import model.Person.SeniorEngineer;
import model.ServiceSummary;
import model.Vehicle;
import org.hibernate.Session;

import java.util.List;
import java.util.Set;

/**
 * A class handling database operations
 */
public class HibernateDBUtil {

    public static void saveNewClient(Client client, Address address) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Logger.getInstance().print("Attempting to add new client", SeverityType.INFO);
            session.beginTransaction();
            client.addAddress(address);
            session.save(client);
            session.save(address);
            session.getTransaction().commit();
            Logger.getInstance().print("New client added", SeverityType.INFO);
        }
    }

    public static Client getClient(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Logger.getInstance().print("Fetching client info", SeverityType.INFO);
            return session.createQuery("from client cl where cl.id = : id", Client.class)
                    .setParameter("id", id)
                    .list().get(0);
        }
    }

    public static void saveVehicleServiceSummary(long vehicleId, ServiceSummary summary) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Logger.getInstance().print("Attempting to save new service summary", SeverityType.INFO);
            session.beginTransaction();
            Vehicle vehicle = session.createQuery("from vehicle vc where vc.id =: id", Vehicle.class)
                    .setParameter("id", vehicleId).list().get(0);
            vehicle.addServiceSummary(summary);
            session.update(vehicle);
            session.save(summary);
            session.getTransaction().commit();
            Logger.getInstance().print("Added new service summary", SeverityType.INFO);
        }
    }

    public static void updateServiceSummary(long summaryId, String title, String description, boolean approved) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Logger.getInstance().print("Attempting update service summary id: " + summaryId, SeverityType.INFO);
            session.beginTransaction();
            ServiceSummary summary = session.createQuery("from serviceSummary ss where ss.id =: id", ServiceSummary.class)
                    .setParameter("id", summaryId).list().get(0);
            summary.setTitle(title);
            summary.setDescription(description);
            summary.setApproved(approved);
            session.update(summary);
            session.getTransaction().commit();
            Logger.getInstance().print("Updated service summary id: " + summaryId , SeverityType.INFO);
        }
    }

    public static void saveSeniorEngineer(SeniorEngineer engineer) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Logger.getInstance().print("Attempting to add new senior engineer", SeverityType.INFO);
            session.beginTransaction();
            session.save(engineer);
            session.getTransaction().commit();
            Logger.getInstance().print("Added new senior engineer", SeverityType.INFO);
        }
    }

    public static void addVehicleToClient(long clientId, Vehicle vehicle) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Logger.getInstance().print("Attempting to add new vehicle for client", SeverityType.INFO);
            session.beginTransaction();
            Client client = session.createQuery("from client cl where cl.id = : id", Client.class).
                    setParameter("id", clientId).list().get(0);
            client.addVehicle(vehicle);
            session.update(client);
            session.save(vehicle);
            session.getTransaction().commit();
            Logger.getInstance().print("Added new vehicle", SeverityType.INFO);
        }
    }

    public static List<ServiceSummary> searchServiceSummaries(String registrationNumber){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Logger.getInstance().print("Fetching service summaries for vehicle: " + registrationNumber, SeverityType.INFO);
            return session.createQuery("from serviceSummary ss where ss.vehicle.registrationNumber =: regNum", ServiceSummary.class)
                    .setParameter("regNum", registrationNumber).list();
        }
    }
}
