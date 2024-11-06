package fatec.sigafx.controller;

import fatec.sigafx.dao.UsuarioDAO;
import fatec.sigafx.model.admin.AdminModel;
import fatec.sigafx.model.aluno.AlunoModel;
import fatec.sigafx.model.professor.ProfessorModel;
import fatec.sigafx.model.usuario.UsuarioModel;
import fatec.sigafx.view.AdmView;
import fatec.sigafx.view.AlunoView;
import fatec.sigafx.view.ProfessorView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    private static final UsuarioDAO dao = new UsuarioDAO();

    @FXML
    private TextField usuarioNome;

    @FXML
    private PasswordField usuarioSenha;

    @FXML
    private Label mensagemErroLogin;

    @FXML
    private void confirmarLogin() {

        //TODO: mover validação para o Model
        UsuarioModel u = dao.buscarPorNome(usuarioNome.getText());
        if (u != null && u.getSenha().equals(usuarioSenha.getText())) {
            if (u instanceof AdminModel) {
                AdmView.mostraAdm();
            }

            if (u instanceof AlunoModel) {
                AlunoView.mostraAluno();
            }

            if (u instanceof ProfessorModel) {
                ProfessorView.mostraProf();
            }
        } else {
            mensagemErroLogin.setVisible(true);
        }
    }
}