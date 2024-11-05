package fatec.sigafx;

import fatec.sigafx.view.AdmView;
import fatec.sigafx.view.AlunoView;
import fatec.sigafx.view.LoginView;
import fatec.sigafx.view.ProfessorView;
import javafx.application.Application;
import javafx.stage.Stage;

public class SigaApplication extends Application
{

    private static Stage stage;

    @Override
    public void start(Stage stage) {
        SigaApplication.stage = stage;

        //LoginView.mostrarLogin();

        //Usar para testar a página de ADM
        //AdmView.mostraAdm();

        //Usar para testar a página de Aluno
        //AlunoView.mostraAluno();

        //Usar para testar a página de Professor
        ProfessorView.mostraProf();
    }

    public static Stage getStage() {
        return stage;
    }

    public static void main(String[] args)
    {
        launch();
    }
}