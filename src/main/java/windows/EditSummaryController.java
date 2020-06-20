package windows;

import db_util.HibernateDBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import logging.Logger;
import logging.SeverityType;

import java.net.URL;
import java.util.ResourceBundle;

public class EditSummaryController {

    @FXML
    private TableView<ModelTable> table;

    private ObservableList<ModelTable> observableList = FXCollections.observableArrayList();

    @FXML
    private TextField regNumSearchField;


    public void onEditButtonPressed(ActionEvent event){
        Logger.getInstance().print("Edit button pressed", SeverityType.DEBUG);
    }

    public void onSearchButtonPressed(ActionEvent event){
        Logger.getInstance().print(regNumSearchField.getText(), SeverityType.INFO);
        HibernateDBUtil.searchServiceSummaries(regNumSearchField.getText());
    }

    public void onCancelButtonPressed(ActionEvent event){

    }


}
