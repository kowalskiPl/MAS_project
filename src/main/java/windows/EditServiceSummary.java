package windows;

import db_util.HibernateDBUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logging.LogPolicy;
import logging.Logger;
import logging.SeverityType;
import model.Airplane;
import model.Helicopter;
import model.Person.Address;
import model.Person.Client;
import model.ServiceSummary;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EditServiceSummary extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
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
        ServiceSummary summary = new ServiceSummary(new SimpleDateFormat("dd-MM-yyyy")
                .parse("21-10-2019"), "test_2", "Test_2 description");
        HibernateDBUtil.saveVehicleServiceSummary(1, summary);
        Parent root = FXMLLoader.load(getClass().getResource("/edit_service_summary.fxml"));
        Logger.getInstance().print("Loaded view", SeverityType.INFO);
        primaryStage.setTitle("Hewwo owo");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        Logger.getInstance().print("Scene shown", SeverityType.INFO);


    }

    public static void main(String[] args) {
        Logger.getInstance().initialize(new LogPolicy(), "log.txt");
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Logger.getInstance().print("Window closing", SeverityType.INFO);
        Logger.getInstance().deinitialize();
    }
}
