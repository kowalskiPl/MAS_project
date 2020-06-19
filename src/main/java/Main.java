import db_util.HibernateUtil;
import model.Person.Address;
import model.Person.Client;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Address address = new Address("Szlifierska 21/31", "Warsaw", "01-461");
        Client client = new Client("John", "Doe", "608810369", address);
        session.save(address);
        session.save(client);
        session.getTransaction().commit();
        System.out.println(session.createQuery("from client ").list());
        session.close();
    }
}
