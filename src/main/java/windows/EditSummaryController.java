package windows;

import db_util.HibernateDBUtil;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import logging.Logger;
import logging.SeverityType;
import model.ServiceSummary;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Javafx controller for edit_service_summary.fxml
 */
public class EditSummaryController implements Initializable {

    @FXML
    private TableView<ModelTable> table;

    private ObservableList<ModelTable> observableList = FXCollections.observableArrayList();

    private EditSummarySecondController secondController;

    @FXML
    private TextField regNumSearchField;

    /**
     * Shows summary edit view
     */
    public void onEditButtonPressed() {
        if (table.getSelectionModel().selectedIndexProperty().get() >= 0){
            Logger.getInstance().print("Edit button pressed", SeverityType.DEBUG);
            ServiceSummary summary = EditServiceSummary.getSummaries().get(table.getSelectionModel().selectedIndexProperty().get());
            EditServiceSummary.setView(1);
            secondController.setApproved(summary.isApproved()? "YES" : "NO");
            secondController.setDate(summary.getDate().toString());
            secondController.setDescription(summary.getDescription());
            secondController.setTitle(summary.getTitle());
            secondController.setRegNum(regNumSearchField.getText());
            secondController.setSummary(summary);
        }
    }

    /**
     * Searches for summaries for given registration number
     */
    public void onSearchButtonPressed() {
        observableList.clear();
        List<ServiceSummary> things = HibernateDBUtil.searchServiceSummaries(regNumSearchField.getText());
        Logger.getInstance().print("Search result for: " + regNumSearchField.getText() + " " + things.toString(), SeverityType.DEBUG);
        for (ServiceSummary thing : things) {
            observableList.add(new ModelTable(thing.getId(), thing.getDate().toString(), thing.getTitle(), thing.isApproved()));
            EditServiceSummary.getSummaries().add(thing);
        }
        table.setItems(observableList);
        table.refresh();
    }


    public void onCancelButtonPressed() {
        Platform.exit();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableColumn<ModelTable, String> colID = new TableColumn<>("id");
        TableColumn<ModelTable, String> colDate = new TableColumn<>("Date");
        TableColumn<ModelTable, String> colTitle = new TableColumn<>("Title");
        TableColumn<ModelTable, String> colApproved = new TableColumn<>("Approved");
        colID.setMinWidth(10.0);
        colDate.setMinWidth(40.0);
        colTitle.setMinWidth(150.0);
        colApproved.setMinWidth(20.0);
        colID.setCellValueFactory(param -> new SimpleStringProperty(Long.toString(param.getValue().getId())));
        colDate.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getDate()));
        colTitle.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getTitle()));
        colApproved.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getApproved()));
        table.getColumns().add(colID);
        table.getColumns().add(colDate);
        table.getColumns().add(colTitle);
        table.getColumns().add(colApproved);
        Logger.getInstance().print("Primary view initialized", SeverityType.DEBUG);

        regNumSearchField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER){
                onSearchButtonPressed();
            }
        });
    }

    public EditSummarySecondController getSecondController() {
        return secondController;
    }

    public void setSecondController(EditSummarySecondController secondController) {
        this.secondController = secondController;
    }

}
