package lk.ijse.the_thirsty_manager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/LogInPage.fxml"));
        stage.setResizable(false);
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

}
