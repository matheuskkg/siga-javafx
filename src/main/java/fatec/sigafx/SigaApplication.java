package fatec.sigafx;

import fatec.sigafx.dao.TurmaDAO;
import fatec.sigafx.model.aulas.DisciplinaModel;
import fatec.sigafx.model.aulas.NotaModel;
import fatec.sigafx.model.aulas.TurmaModel;
import fatec.sigafx.model.aulas.dto.DisciplinaCriarRequest;
import fatec.sigafx.model.usuarios.AlunoModel;
import fatec.sigafx.model.usuarios.ProfessorModel;
import fatec.sigafx.view.LoginView;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import fatec.sigafx.model.usuarios.UsuarioModel;
import fatec.sigafx.model.usuarios.dto.UsuarioCriarRequest;

import java.util.Objects;
//
public class SigaApplication extends Application
{

    private static Stage stage;

    @Override
    public void start(Stage stage) {
        if (UsuarioModel.buscarUsuarioPorEmail("aluno@aluno") == null) {
            UsuarioModel.criarUsuario(new UsuarioCriarRequest("aluno", "aluno@aluno", "aluno"), "Aluno");
            UsuarioModel.criarUsuario(new UsuarioCriarRequest("admin", "admin@admin", "admin"), "Administrador");
            UsuarioModel.criarUsuario(new UsuarioCriarRequest("professor", "professor@professor", "professor"), "Professor");
        }

        SigaApplication.stage = stage;

        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/icone.png"))));

        LoginView.mostrarLogin();

        //Usar para testar a página de ADM
        //AdminView.mostrarHomeAdmin();

        //Usar para testar a página de Aluno
        //AlunoView.mostrarHomeAluno();

        //Usar para testar a página de Professor
        //ProfessorView.mostrarHomeProf();
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