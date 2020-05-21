import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private TextField nameEntry;

    @FXML
    private TextField firmNameEntry;

    @FXML
    private TextField practiceAreaEntry;

    @FXML
    private TextField specialityEntry;

    @FXML
    private TextField jobTitleEntry;

    @FXML
    private TextField ethnicityEntry;

    @FXML
    private TextField admissionDateEntry;

    @FXML
    private TextField admissionJuristictionEntry;

    @FXML
    private TextField firmProfileEntry;

    @FXML
    private TextField linkedinProfileEntry;

    @FXML
    private TextField approachedEntry;

    @FXML
    private TextField phoneNoEntry;

    @FXML
    private TextField emailEntry;

    @FXML
    private TextField statusEntry;

    @FXML
    private Button addEntryButton;

    @FXML
    private Button resetButton;

    @FXML
    private TableView<People> tableView;

    @FXML
    private TextField searchBar;

    @FXML
    private TableColumn<People, Integer> idCol;

    @FXML
    private TableColumn<People, String> nameCol;

    @FXML
    private TableColumn<People, String> firmCol;

    @FXML
    private TableColumn<People, String> practiseAreaCol;

    @FXML
    private TableColumn<People, String> specialityCol;

    @FXML
    private TableColumn<People, String> jobTitleCol;

    @FXML
    private TableColumn<People, String> ethnicityCol;

    @FXML
    private TableColumn<People, String> admissionDateCol;

    @FXML
    private TableColumn<People, String> admissionJuristictionCol;

    @FXML
    private TableColumn<People, String> firmProfileCol;

    @FXML
    private TableColumn<People, String> linkedinCol;

    @FXML
    private TableColumn<People, String> approchedCol;

    @FXML
    private TableColumn<People, String> phoneNoCol;

    @FXML
    private TableColumn<People, String> emailCol;

    @FXML
    private TableColumn<People, String> statusCol;


    ObservableList<People> listM;

    ContextMenu contextMenu = new ContextMenu();
    MenuItem miView = new MenuItem("View");
    MenuItem miEdit = new MenuItem("Edit");
    MenuItem miRemove = new MenuItem("Remove");

    private static FilteredList<People> filteredData;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        firmCol.setCellValueFactory(new PropertyValueFactory<>("firmName"));
        practiseAreaCol.setCellValueFactory(new PropertyValueFactory<>("practiseArea"));
        specialityCol.setCellValueFactory(new PropertyValueFactory<>("speciality"));
        jobTitleCol.setCellValueFactory(new PropertyValueFactory<>("jobTitle"));
        ethnicityCol.setCellValueFactory(new PropertyValueFactory<>("ethnicity"));
        admissionDateCol.setCellValueFactory(new PropertyValueFactory<>("admissionDate"));
        admissionJuristictionCol.setCellValueFactory(new PropertyValueFactory<>("admissionJuristiction"));
        firmProfileCol.setCellValueFactory(new PropertyValueFactory<>("firmProfile"));
        linkedinCol.setCellValueFactory(new PropertyValueFactory<>("linkedinProfile"));
        approchedCol.setCellValueFactory(new PropertyValueFactory<>("approached"));
        phoneNoCol.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        contextMenu.getItems().add(miView);
        contextMenu.getItems().add(miRemove);

        updateTable();

        // Search field

        filteredData = new FilteredList<>(listM, b -> true);
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(People -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (People.getName().toLowerCase().contains(lowerCaseFilter) && !People.getName().equals(null)) {
                    return true;
                } else if (People.getFirmName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (People.getSpeciality() != null && People.getSpeciality().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (People.getPractiseArea() != null && People.getPractiseArea().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (People.getEmail() != null && People.getEmail().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (People.getAdmissionDate() != null && People.getAdmissionDate().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (People.getStatus() != null && People.getStatus().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (People.getPhoneNo() != null && People.getPhoneNo().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (People.getLinkedinProfile() != null && People.getLinkedinProfile().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (People.getJobTitle() != null && People.getJobTitle().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (People.getEthnicity() != null && People.getEthnicity().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (People.getApproached() != null && People.getApproached().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (People.getAdmissionJuristiction() != null && People.getAdmissionJuristiction().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<People> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);



        // Record clicks
        tableView.setRowFactory(tv -> {
            TableRow<People> row = new TableRow<>();


            row.setOnMouseClicked(event -> {
                People rowData = row.getItem();
                contextMenu.hide();

                miView.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent t) {
                        try {
                            buildProfile(rowData);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

                miRemove.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent t) {
                        Database.removeEntry(rowData);
                        updateTable();
                    }
                });

                // Double click
                if (event.getClickCount() == 2 && (!row.isEmpty()) && event.getButton() == MouseButton.PRIMARY) {
                    try {
                        buildProfile(rowData);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                // Right click
                if (event.getButton() == MouseButton.SECONDARY) {
                    contextMenu.show(tableView, event.getScreenX(), event.getScreenY());
                }
            });
            return row;
        });
    }


    public void addEntryButtonPressed(Event e) {
        String emptyRecords = checkRecordsEmpty();
        System.out.println(emptyRecords);
        if (emptyRecords.equals("")) {
            People newEntry = new People(0, nameEntry.getText(), firmNameEntry.getText(), practiceAreaEntry.getText(), specialityEntry.getText(), jobTitleEntry.getText(),
                    ethnicityEntry.getText(), admissionDateEntry.getText(), admissionJuristictionEntry.getText(), firmProfileEntry.getText(), linkedinProfileEntry.getText(), approachedEntry.getText(),
                    phoneNoEntry.getText(), emailEntry.getText(), statusEntry.getText());
            Database.addEntry(newEntry);
            updateTable();
            resetEntryFields();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("The following fields cannot be empty:" + emptyRecords);
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();

        }
    }

    private String checkRecordsEmpty() {
        String emptyFields = "";
        if (nameEntry.getText().isEmpty()) {
            emptyFields = emptyFields.concat("\n" + nameEntry.getPromptText());
        }
        if (firmNameEntry.getText().isEmpty()) {
            emptyFields = emptyFields.concat("\n" + firmNameEntry.getPromptText());
        }
        return emptyFields;
    }

    public void updateTable() {



        listM = Database.getPeople();

        filteredData = new FilteredList<>(listM, b -> true);
        SortedList<People> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);
    }

    private void buildProfile(People rowData) throws IOException {
        FXMLLoader loader = new FXMLLoader(profileController.class.getClassLoader().getResource("personProfile.fxml"));
        Parent root = loader.load();
        profileController profileController = loader.getController();
        profileController.changeLabels(rowData);
        profileController.showComments();

        Stage stage = new Stage();
        stage.setTitle(rowData.getName());
        stage.setScene(new Scene(root, 975, 600));

        Image icon = new Image("file:assets/navigateIcon2.png");
        stage.getIcons().add(icon);

        stage.show();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                updateTable();
            }
        });

    }

    public void resetButtonClicked(Event e) {
        resetEntryFields();
    }

    private void resetEntryFields(){
        nameEntry.setText("");
        firmNameEntry.setText("");
        practiceAreaEntry.setText("");
        specialityEntry.setText("");
        jobTitleEntry.setText("");
        ethnicityEntry.setText("");
        admissionDateEntry.setText("");
        admissionJuristictionEntry.setText("");
        firmProfileEntry.setText("");
        linkedinProfileEntry.setText("");
        approachedEntry.setText("");
        phoneNoEntry.setText("");
        emailEntry.setText("");
        statusEntry.setText("");
    }


    // Remove top menu button
    public void removeMenuButtonClick(Event e){
        System.out.println("yes");
    }

}
