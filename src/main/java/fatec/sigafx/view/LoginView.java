package fatec.sigafx.view;

import fatec.sigafx.SigaApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class LoginView {
    public static void mostrarLogin() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(LoginView.class.getResource("/fxml/login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 900, 600);

            SigaApplication.getStage().setTitle("SIGA - Login");
            SigaApplication.getStage().setScene(scene);
            SigaApplication.getStage().show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
