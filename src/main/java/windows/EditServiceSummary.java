package windows;

import db_util.HibernateDBUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logging.LogPolicy;
import logging.Logger;
import logging.SeverityType;
import model.Airplane;
import model.Helicopter;
import model.Person.Address;
import model.Person.Client;
import model.ServiceSummary;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EditServiceSummary extends Application {
    private static AnchorPane root = new AnchorPane();
    private static List<AnchorPane> views = new ArrayList<>();
    private static List<ServiceSummary> summaries = new ArrayList<>();
    private static int currentView = 0;

    @Override
    public void start(Stage primaryStage) {
        try {
            prepareData();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        FXMLLoader lod = new FXMLLoader();
        try {
            views.add(lod.load(getClass().getResource("/edit_service_summary.fxml").openStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        EditSummaryController con = lod.getController();

        FXMLLoader loader = new FXMLLoader();
        try {
            views.add(loader.load(getClass().getResource("/edit_service_second.fxml").openStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        EditSummarySecondController c = loader.getController();
        con.setSecondController(c);
        c.setController(con);

        setView(0);
        primaryStage.setTitle("Edit service summary");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static List<ServiceSummary> getSummaries() {
        return summaries;
    }

    public static void setSummaries(List<ServiceSummary> summaries) {
        EditServiceSummary.summaries = summaries;
    }

    public static void setView(int id) {
        root.getChildren().remove(views.get(currentView));
        root.getChildren().add(views.get(id));
        currentView = id;
        Logger.getInstance().print("Showing scene id: "+ id, SeverityType.INFO);
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

    private void prepareData() throws ParseException {
        Address address = new Address("Szlifierska 21/31", "Warsaw", "01-461");
        Client client = new Client("John", "Doe", "608810369", address);
        Address address1 = new Address("Kluczborska 11/16", "Warsaw", "01-461");
        Client client1 = new Client("Kurt", "Tank", "608810369", address);
        HibernateDBUtil.saveNewClient(client, address);
        HibernateDBUtil.saveNewClient(client1, address1);
        Airplane airplane = new Airplane();
        airplane.setRegistrationNumber("SP-ABC");
        Helicopter helicopter = new Helicopter();
        helicopter.setRegistrationNumber("SP-KEKW");
        HibernateDBUtil.addVehicleToClient(1, airplane);
        HibernateDBUtil.addVehicleToClient(2, helicopter);

        ServiceSummary serviceSummary = new ServiceSummary(new Date(), "Engine test", "Tested engine parameters, changed oil");
        HibernateDBUtil.saveVehicleServiceSummary(1, serviceSummary);
        ServiceSummary summary = new ServiceSummary(new SimpleDateFormat("dd-MM-yyyy")
                .parse("21-10-2019"), "Changed elevator", "Elevator got replaced with a new one.");
        HibernateDBUtil.saveVehicleServiceSummary(1, summary);

        ServiceSummary serviceHeli_1 = new ServiceSummary(new SimpleDateFormat("dd-MM-yyyy")
                .parse("11-07-2018"), "Replaced main rotor blade", "Blade number 3 was damaged after a collision with bird" +
                "so it got replaced with a new one", true);

        ServiceSummary serviceHeli_2 = new ServiceSummary(new Date(), "Changed engine oil", "A regular scheduled oil change in both engines");
        ServiceSummary serviceHeli_3 = new ServiceSummary(new SimpleDateFormat("dd-MM-yyyy")
                .parse("14-04-2020"), "Right engine replaced", "After mechanical failure right engine compressor got completely" +
                "destroyed and engine had to be replaced with new one");

        HibernateDBUtil.saveVehicleServiceSummary(2, serviceHeli_1);
        HibernateDBUtil.saveVehicleServiceSummary(2, serviceHeli_2);
        HibernateDBUtil.saveVehicleServiceSummary(2, serviceHeli_3);
    }
}
