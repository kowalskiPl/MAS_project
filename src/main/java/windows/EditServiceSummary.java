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
    public void start(Stage primaryStage) throws Exception {
        Address address = new Address("Szlifierska 21/31", "Warsaw", "01-461");
        Client client = new Client("John", "Doe", "608810369", address);
        HibernateDBUtil.saveNewClient(client, address);
        Airplane airplane = new Airplane();
        airplane.setRegistrationNumber("EF-ABC");
        Helicopter helicopter = new Helicopter();
        HibernateDBUtil.addVehicleToClient(1, airplane);
        HibernateDBUtil.addVehicleToClient(1, helicopter);

        ServiceSummary serviceSummary = new ServiceSummary(new Date(), "Engine test", "Tested engine parameters, changed oil");
        HibernateDBUtil.saveVehicleServiceSummary(1, serviceSummary);
        ServiceSummary summary = new ServiceSummary(new SimpleDateFormat("dd-MM-yyyy")
                .parse("21-10-2019"), "Changed elevator", "Elevator got replaced with a new one.");
        HibernateDBUtil.saveVehicleServiceSummary(1, summary);



        FXMLLoader lod = new FXMLLoader();
        views.add(lod.load(getClass().getResource("/edit_service_summary.fxml").openStream()));
        EditSummaryController con = lod.getController();

        FXMLLoader loader = new FXMLLoader();
        views.add(loader.load(getClass().getResource("/edit_service_second.fxml").openStream()));
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
}
