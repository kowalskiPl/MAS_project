package windows;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logging.LogPolicy;
import logging.Logger;
import logging.SeverityType;

public class EditServiceSummary extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
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
