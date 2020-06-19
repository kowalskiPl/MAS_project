package db_util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            if (sessionFactory == null) {
                Configuration configuration = new Configuration();

                configuration.addAnnotatedClass(model.Vehicle.class);
                configuration.addAnnotatedClass(model.Airplane.class);
                configuration.addAnnotatedClass(model.Manufacturer.class);
                configuration.addAnnotatedClass(model.Helicopter.class);
                configuration.addAnnotatedClass(model.ServiceSummary.class);
                configuration.addAnnotatedClass(model.Parts.NavigationSystem.class);
                configuration.addAnnotatedClass(model.Parts.Engine.class);
                configuration.addAnnotatedClass(model.Parts.PistonEngine.class);
                configuration.addAnnotatedClass(model.Parts.GasTurbineEngine.class);
                configuration.addAnnotatedClass(model.Parts.RotorBlade.class);
                configuration.addAnnotatedClass(model.Parts.Part.class);

                configuration.addAnnotatedClass(model.Person.Person.class);
                configuration.addAnnotatedClass(model.Person.Client.class);
                configuration.addAnnotatedClass(model.Person.Address.class);
                configuration.addAnnotatedClass(model.Person.Employee.class);
                configuration.addAnnotatedClass(model.Person.JuniorEngineer.class);
                configuration.addAnnotatedClass(model.Person.SeniorEngineer.class);

                configuration.configure("hibernate.cfg.xml");
                StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
                serviceRegistryBuilder.applySettings(configuration.getProperties());
                ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            }
            return sessionFactory;
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }

}
