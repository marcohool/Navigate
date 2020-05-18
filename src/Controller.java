import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    @FXML
    private TextField unameField;

    @FXML
    private PasswordField pwordField;


    public void loginButtonPressed(Event event) throws IOException {

        if (unameField.getText().equals("123") && pwordField.getText().equals("123")) {
            System.out.println("cool");

            Parent mainParent = FXMLLoader.load(getClass().getResource("mainView.fxml"));
            Scene mainScene = new Scene(mainParent);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(mainScene);
            window.show();
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            window.setX((primScreenBounds.getWidth() - window.getWidth()) / 2);
            window.setY((primScreenBounds.getHeight() - window.getHeight()) / 2);

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
