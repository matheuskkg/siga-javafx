package fatec.sigafx.view;

import fatec.sigafx.SigaApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class HomeView {
    public static void mostrarHome() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HomeView.class.getResource("/fxml/home.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 800);

            SigaApplication.getStage().setTitle("SIGA - Home");
            SigaApplication.getStage().setScene(scene);
            SigaApplication.getStage().show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
