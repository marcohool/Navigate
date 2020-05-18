import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.xml.soap.Text;
import java.awt.*;

public class Controller {

    @FXML
    private TextField unameField;

    @FXML
    private PasswordField pwordField;


    public void loginButtonPressed(Event event){
        if (unameField.getText().equals("123") && pwordField.getText().equals("123")) {
            System.out.println("cool");
            
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Verification Error");
            alert.setHeaderText(null);
            alert.setContentText("Incorrect username or password");
            alert.showAndWait();
        }
    }

    public void usernameField(Event event){

    }

    public void pwordField(Event event){

    }


}
