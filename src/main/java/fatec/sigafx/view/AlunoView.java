package fatec.sigafx.view;

import fatec.sigafx.SigaApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class AlunoView {
    public static void mostrarHomeAluno(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(SigaApplication.class.getResource("/fxml/aluno.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1280, 800);

            SigaApplication.getStage().setTitle("SIGA - Aluno");
            SigaApplication.getStage().setScene(scene);
            SigaApplication.getStage().show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
