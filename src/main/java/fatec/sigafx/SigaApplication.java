package fatec.sigafx;

import fatec.sigafx.view.LoginView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SigaApplication extends Application {

    @Override
    public void start(Stage stage) {
        LoginView login = new LoginView(stage);
        login.mostrarLogin();
    }

    public static void main(String[] args) {
        launch();
    }
}