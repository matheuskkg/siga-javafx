package fatec.sigafx;

import fatec.sigafx.dao.AlunoDAO;
import fatec.sigafx.dao.NotaDAO;
import fatec.sigafx.dao.UsuarioDAO;
import fatec.sigafx.model.admin.AdminModel;
import fatec.sigafx.model.aluno.AlunoModel;
import fatec.sigafx.model.professor.ProfessorModel;
import fatec.sigafx.model.usuario.dto.UsuarioCriarRequest;
import fatec.sigafx.view.AdmView;
import fatec.sigafx.view.AlunoView;
import fatec.sigafx.view.LoginView;
import fatec.sigafx.view.ProfessorView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Turma -> Disciplina, professor, alunos
 * aluno -> Disciplina -> Nota
 */

public class SigaApplication extends Application
{

    private static Stage stage;

    @Override
    public void start(Stage stage) {
        SigaApplication.stage = stage;

        LoginView.mostrarLogin();

        //Usar para testar a página de ADM
        //AdmView.mostraAdm();

        //Usar para testar a página de Aluno
        //AlunoView.mostraAluno();

        //Usar para testar a página de Professor
        //ProfessorView.mostraProf();

        //Apenas para testes
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        AlunoModel a = new AlunoModel(new UsuarioCriarRequest("aluno", "aluno@aluno", "aluno"));
        usuarioDAO.salvarUsuario(a);

        AdminModel b = new AdminModel(new UsuarioCriarRequest("admin", "admin@admin", "admin"));
        usuarioDAO.salvarUsuario(b);

        ProfessorModel c = new ProfessorModel(new UsuarioCriarRequest("professor", "professor@professor", "professor"));
        usuarioDAO.salvarUsuario(c);
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