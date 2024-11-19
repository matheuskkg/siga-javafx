package fatec.sigafx;

import fatec.sigafx.dao.TurmaDAO;
import fatec.sigafx.model.aulas.DisciplinaModel;
import fatec.sigafx.model.aulas.NotaModel;
import fatec.sigafx.model.aulas.TurmaModel;
import fatec.sigafx.model.aulas.dto.AlunoNotasResponse;
import fatec.sigafx.model.aulas.dto.DisciplinaCriarRequest;
import fatec.sigafx.model.usuarios.AlunoModel;
import fatec.sigafx.model.usuarios.ProfessorModel;
import fatec.sigafx.view.LoginView;
import fatec.sigafx.view.AdminView;
import fatec.sigafx.view.AlunoView;
import fatec.sigafx.view.ProfessorView;
import javafx.application.Application;
import javafx.stage.Stage;

import fatec.sigafx.model.usuarios.UsuarioModel;
import fatec.sigafx.model.usuarios.dto.UsuarioCriarRequest;

import java.util.List;

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

        TurmaDAO dao = new TurmaDAO();
        List<TurmaModel> turmas = dao.buscarTodos();

        for (TurmaModel t : turmas) {
            List<AlunoNotasResponse> alunos = t.getAlunosComNotas();

            for (AlunoNotasResponse a : alunos) {
                System.out.println(a);
            }
        }

        SigaApplication.stage = stage;

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