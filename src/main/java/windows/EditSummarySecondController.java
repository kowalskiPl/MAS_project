package windows;

import db_util.HibernateDBUtil;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.ServiceSummary;

import java.net.URL;
import java.util.ResourceBundle;


public class EditSummarySecondController implements Initializable {

    @FXML
    private Label regNumLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label approvedLabel;

    @FXML
    private TextField titleTextField;

    @FXML
    private TextArea descriptionLabel;

    private EditSummaryController controller;

    private ServiceSummary summary;

    private boolean changed = false;

    public void onExitButtonPressed() {
        Platform.exit();
    }

    public void onReturnButtonPressed(ActionEvent event) {
        EditServiceSummary.setView(0);
        if (changed)
            controller.onSearchButtonPressed(event);
    }

    public void onSaveChangesPressed() {
        if (!summary.getTitle().equals(titleTextField.getText()) || !summary.getDescription().equals(descriptionLabel.getText())) {
            summary.setDescription(descriptionLabel.getText());
            summary.setTitle(titleTextField.getText());
            HibernateDBUtil.updateServiceSummary(summary);
            changed = true;
        }
    }

    public void onApprovedPressed() {
        if (!summary.isApproved()) {
            summary.setApproved(true);
            HibernateDBUtil.updateServiceSummary(summary);
            approvedLabel.setText("YES");
            changed = true;
        }
    }

    public void setRegNum(String s) {
        regNumLabel.textProperty().setValue(s);
    }

    public void setDate(String s) {
        dateLabel.textProperty().setValue(s);
    }

    public void setApproved(String s) {
        approvedLabel.textProperty().setValue(s);
    }

    public void setTitle(String s) {
        titleTextField.textProperty().setValue(s);
    }

    public void setDescription(String s) {
        descriptionLabel.textProperty().setValue(s);
    }

    public Label getRegNum() {
        return regNumLabel;
    }

    public Label getDate() {
        return dateLabel;
    }

    public Label getApproved() {
        return approvedLabel;
    }

    public TextField getTitle() {
        return titleTextField;
    }

    public TextArea getDescription() {
        return descriptionLabel;
    }

    public EditSummaryController getController() {
        return controller;
    }

    public void setController(EditSummaryController controller) {
        this.controller = controller;
    }

    public ServiceSummary getSummary() {
        return summary;
    }

    public void setSummary(ServiceSummary summary) {
        this.summary = summary;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        regNum = new Label();
//        date = new Label();
//        title = new TextField();
//        approved = new Label();
//        description = new TextArea();
    }
}
