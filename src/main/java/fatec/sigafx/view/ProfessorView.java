package fatec.sigafx.view;

import fatec.sigafx.SigaApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class ProfessorView {
    public static void mostrarHomeProf(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(SigaApplication.class.getResource("/fxml/prof.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 900, 600);

            SigaApplication.getStage().setTitle("SIGA - Professor");
            SigaApplication.getStage().setScene(scene);
            SigaApplication.getStage().show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
