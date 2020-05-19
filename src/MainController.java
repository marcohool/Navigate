import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    // Main scene

    @FXML
    private TableView<People> tableView;

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
    int index = -1;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //idCol.setCellValueFactory(new PropertyValueFactory<People, Integer>("personID"));
        nameCol.setCellValueFactory(new PropertyValueFactory<People, String>("name"));
        firmCol.setCellValueFactory(new PropertyValueFactory<People, String>("firmName"));
        practiseAreaCol.setCellValueFactory(new PropertyValueFactory<People, String>("practiseArea"));
        specialityCol.setCellValueFactory(new PropertyValueFactory<People, String>("speciality"));
        jobTitleCol.setCellValueFactory(new PropertyValueFactory<People, String>("jobTitle"));
        ethnicityCol.setCellValueFactory(new PropertyValueFactory<People, String>("ethnicity"));
        admissionDateCol.setCellValueFactory(new PropertyValueFactory<People, String>("admissionDate"));
        admissionJuristictionCol.setCellValueFactory(new PropertyValueFactory<People, String>("admissionJuristiction"));
        firmProfileCol.setCellValueFactory(new PropertyValueFactory<People, String>("firmProfile"));
        linkedinCol.setCellValueFactory(new PropertyValueFactory<People, String>("linkedinProfile"));
        approchedCol.setCellValueFactory(new PropertyValueFactory<People, String>("approached"));
        phoneNoCol.setCellValueFactory(new PropertyValueFactory<People, String>("phoneNo"));
        emailCol.setCellValueFactory(new PropertyValueFactory<People, String>("email"));
        statusCol.setCellValueFactory(new PropertyValueFactory<People, String>("status"));


        listM = Database.getPeople();
        tableView.setItems(listM);

        tableView.setRowFactory( tv -> {
            TableRow<People> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    People rowData = row.getItem();
                    System.out.println(rowData.getName());
                }
            });
            return row ;
        });

    }

//    public void rowMouseClick(Event event){
//        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
//            Item rowData = row.getItem();
//            System.out.println("Double click on: "+rowData.getName());
//        }
//    }



}
