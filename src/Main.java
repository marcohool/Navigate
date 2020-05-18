import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Navigate");
        primaryStage.setScene(new Scene(root, 500, 450));

        Image icon = new Image("file:assets/navigateIcon2.png");
        primaryStage.getIcons().add(icon);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
