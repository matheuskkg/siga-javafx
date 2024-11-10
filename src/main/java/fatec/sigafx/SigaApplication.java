package fatec.sigafx;

import javafx.application.Application;
import javafx.stage.Stage;
import fatec.sigafx.view.LoginView;

import fatec.sigafx.model.usuario.UsuarioModel;
import fatec.sigafx.model.usuario.dto.UsuarioCriarRequest;
import fatec.sigafx.view.AlunoView;
import fatec.sigafx.view.ProfessorView;
import fatec.sigafx.view.AdminView;

public class SigaApplication extends Application
{

    private static Stage stage;

    @Override
    public void start(Stage stage) {
        SigaApplication.stage = stage;

        LoginView.mostrarLogin();

        //Usar para testar a página de ADM
        //AdminView.mostrarHomeAdmin();

        //Usar para testar a página de Aluno
        //AlunoView.mostrarHomeAluno();

        //Usar para testar a página de Professor
        //ProfessorView.mostrarHomeProf();

        //Apenas para testes
        if (!UsuarioModel.verificarEmailEmUso("aluno@aluno")) {
            UsuarioModel.criarUsuario(new UsuarioCriarRequest("aluno", "aluno@aluno", "aluno"), "Aluno");
            UsuarioModel.criarUsuario(new UsuarioCriarRequest("admin", "admin@admin", "admin"), "Administrador");
            UsuarioModel.criarUsuario(new UsuarioCriarRequest("professor", "professor@professor", "professor"), "Professor");
        }
    }

    public static Stage getStage() {
        return stage;
    }

    public static void main(String[] args)
    {
        launch();
        EMF.close();
    }
}