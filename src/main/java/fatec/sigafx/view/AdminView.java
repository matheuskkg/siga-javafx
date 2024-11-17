package fatec.sigafx.view;

import fatec.sigafx.SigaApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class AdminView {
    public static void mostrarHomeAdmin(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(SigaApplication.class.getResource("/fxml/admin.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 900, 600);

            SigaApplication.getStage().setTitle("SIGA - Administrador");
            SigaApplication.getStage().setScene(scene);
            SigaApplication.getStage().show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
